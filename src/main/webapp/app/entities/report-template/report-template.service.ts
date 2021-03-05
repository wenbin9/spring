import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IReportTemplate } from 'app/shared/model/report-template.model';

type EntityResponseType = HttpResponse<IReportTemplate>;
type EntityArrayResponseType = HttpResponse<IReportTemplate[]>;

@Injectable({ providedIn: 'root' })
export class ReportTemplateService {
  public resourceUrl = SERVER_API_URL + 'api/report-templates';

  constructor(protected http: HttpClient) {}

  create(reportTemplate: IReportTemplate): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reportTemplate);
    return this.http
      .post<IReportTemplate>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(reportTemplate: IReportTemplate): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(reportTemplate);
    return this.http
      .put<IReportTemplate>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IReportTemplate>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IReportTemplate[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(reportTemplate: IReportTemplate): IReportTemplate {
    const copy: IReportTemplate = Object.assign({}, reportTemplate, {
      effectiveDt:
        reportTemplate.effectiveDt && reportTemplate.effectiveDt.isValid() ? reportTemplate.effectiveDt.format(DATE_FORMAT) : undefined,
      expiryDt: reportTemplate.expiryDt && reportTemplate.expiryDt.isValid() ? reportTemplate.expiryDt.format(DATE_FORMAT) : undefined,
      createdDt: reportTemplate.createdDt && reportTemplate.createdDt.isValid() ? reportTemplate.createdDt.format(DATE_FORMAT) : undefined,
      updatedDt: reportTemplate.updatedDt && reportTemplate.updatedDt.isValid() ? reportTemplate.updatedDt.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.effectiveDt = res.body.effectiveDt ? moment(res.body.effectiveDt) : undefined;
      res.body.expiryDt = res.body.expiryDt ? moment(res.body.expiryDt) : undefined;
      res.body.createdDt = res.body.createdDt ? moment(res.body.createdDt) : undefined;
      res.body.updatedDt = res.body.updatedDt ? moment(res.body.updatedDt) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((reportTemplate: IReportTemplate) => {
        reportTemplate.effectiveDt = reportTemplate.effectiveDt ? moment(reportTemplate.effectiveDt) : undefined;
        reportTemplate.expiryDt = reportTemplate.expiryDt ? moment(reportTemplate.expiryDt) : undefined;
        reportTemplate.createdDt = reportTemplate.createdDt ? moment(reportTemplate.createdDt) : undefined;
        reportTemplate.updatedDt = reportTemplate.updatedDt ? moment(reportTemplate.updatedDt) : undefined;
      });
    }
    return res;
  }
}
