import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReportTemplate } from 'app/shared/model/report-template.model';
import { ReportTemplateService } from './report-template.service';

@Component({
  templateUrl: './report-template-delete-dialog.component.html',
})
export class ReportTemplateDeleteDialogComponent {
  reportTemplate?: IReportTemplate;

  constructor(
    protected reportTemplateService: ReportTemplateService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.reportTemplateService.delete(id).subscribe(() => {
      this.eventManager.broadcast('reportTemplateListModification');
      this.activeModal.close();
    });
  }
}
