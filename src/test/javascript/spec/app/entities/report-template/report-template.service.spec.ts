import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ReportTemplateService } from 'app/entities/report-template/report-template.service';
import { IReportTemplate, ReportTemplate } from 'app/shared/model/report-template.model';

describe('Service Tests', () => {
  describe('ReportTemplate Service', () => {
    let injector: TestBed;
    let service: ReportTemplateService;
    let httpMock: HttpTestingController;
    let elemDefault: IReportTemplate;
    let expectedResult: IReportTemplate | IReportTemplate[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ReportTemplateService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ReportTemplate(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'image/png',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        currentDate,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            effectiveDt: currentDate.format(DATE_FORMAT),
            expiryDt: currentDate.format(DATE_FORMAT),
            createdDt: currentDate.format(DATE_FORMAT),
            updatedDt: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ReportTemplate', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            effectiveDt: currentDate.format(DATE_FORMAT),
            expiryDt: currentDate.format(DATE_FORMAT),
            createdDt: currentDate.format(DATE_FORMAT),
            updatedDt: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effectiveDt: currentDate,
            expiryDt: currentDate,
            createdDt: currentDate,
            updatedDt: currentDate,
          },
          returnedFromService
        );

        service.create(new ReportTemplate()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ReportTemplate', () => {
        const returnedFromService = Object.assign(
          {
            templateId: 'BBBBBB',
            templateName: 'BBBBBB',
            templateFileName: 'BBBBBB',
            templateFile: 'BBBBBB',
            effectiveDt: currentDate.format(DATE_FORMAT),
            expiryDt: currentDate.format(DATE_FORMAT),
            status: 'BBBBBB',
            createdBy: 'BBBBBB',
            createdDt: currentDate.format(DATE_FORMAT),
            updatedBy: 'BBBBBB',
            updatedDt: currentDate.format(DATE_FORMAT),
            version: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effectiveDt: currentDate,
            expiryDt: currentDate,
            createdDt: currentDate,
            updatedDt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ReportTemplate', () => {
        const returnedFromService = Object.assign(
          {
            templateId: 'BBBBBB',
            templateName: 'BBBBBB',
            templateFileName: 'BBBBBB',
            templateFile: 'BBBBBB',
            effectiveDt: currentDate.format(DATE_FORMAT),
            expiryDt: currentDate.format(DATE_FORMAT),
            status: 'BBBBBB',
            createdBy: 'BBBBBB',
            createdDt: currentDate.format(DATE_FORMAT),
            updatedBy: 'BBBBBB',
            updatedDt: currentDate.format(DATE_FORMAT),
            version: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effectiveDt: currentDate,
            expiryDt: currentDate,
            createdDt: currentDate,
            updatedDt: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ReportTemplate', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
