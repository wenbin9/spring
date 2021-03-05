import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IReportTemplate } from 'app/shared/model/report-template.model';

@Component({
  selector: 'jhi-report-template-detail',
  templateUrl: './report-template-detail.component.html',
})
export class ReportTemplateDetailComponent implements OnInit {
  reportTemplate: IReportTemplate | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reportTemplate }) => (this.reportTemplate = reportTemplate));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
