import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IReportTemplate, ReportTemplate } from 'app/shared/model/report-template.model';
import { ReportTemplateService } from './report-template.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-report-template-update',
  templateUrl: './report-template-update.component.html',
})
export class ReportTemplateUpdateComponent implements OnInit {
  isSaving = false;
  effectiveDtDp: any;
  expiryDtDp: any;
  createdDtDp: any;
  updatedDtDp: any;

  editForm = this.fb.group({
    id: [],
    templateId: [],
    templateName: [],
    templateFileName: [],
    templateFile: [],
    templateFileContentType: [],
    effectiveDt: [],
    expiryDt: [],
    status: [],
    createdBy: [],
    createdDt: [],
    updatedBy: [],
    updatedDt: [],
    version: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected reportTemplateService: ReportTemplateService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ reportTemplate }) => {
      this.updateForm(reportTemplate);
    });
  }

  updateForm(reportTemplate: IReportTemplate): void {
    this.editForm.patchValue({
      id: reportTemplate.id,
      templateId: reportTemplate.templateId,
      templateName: reportTemplate.templateName,
      templateFileName: reportTemplate.templateFileName,
      templateFile: reportTemplate.templateFile,
      templateFileContentType: reportTemplate.templateFileContentType,
      effectiveDt: reportTemplate.effectiveDt,
      expiryDt: reportTemplate.expiryDt,
      status: reportTemplate.status,
      createdBy: reportTemplate.createdBy,
      createdDt: reportTemplate.createdDt,
      updatedBy: reportTemplate.updatedBy,
      updatedDt: reportTemplate.updatedDt,
      version: reportTemplate.version,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: any, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('springApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const reportTemplate = this.createFromForm();
    if (reportTemplate.id !== undefined) {
      this.subscribeToSaveResponse(this.reportTemplateService.update(reportTemplate));
    } else {
      this.subscribeToSaveResponse(this.reportTemplateService.create(reportTemplate));
    }
  }

  private createFromForm(): IReportTemplate {
    return {
      ...new ReportTemplate(),
      id: this.editForm.get(['id'])!.value,
      templateId: this.editForm.get(['templateId'])!.value,
      templateName: this.editForm.get(['templateName'])!.value,
      templateFileName: this.editForm.get(['templateFileName'])!.value,
      templateFileContentType: this.editForm.get(['templateFileContentType'])!.value,
      templateFile: this.editForm.get(['templateFile'])!.value,
      effectiveDt: this.editForm.get(['effectiveDt'])!.value,
      expiryDt: this.editForm.get(['expiryDt'])!.value,
      status: this.editForm.get(['status'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDt: this.editForm.get(['createdDt'])!.value,
      updatedBy: this.editForm.get(['updatedBy'])!.value,
      updatedDt: this.editForm.get(['updatedDt'])!.value,
      version: this.editForm.get(['version'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IReportTemplate>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
