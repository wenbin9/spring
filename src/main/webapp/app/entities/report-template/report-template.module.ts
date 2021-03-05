import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SpringSharedModule } from 'app/shared/shared.module';
import { ReportTemplateComponent } from './report-template.component';
import { ReportTemplateDetailComponent } from './report-template-detail.component';
import { ReportTemplateUpdateComponent } from './report-template-update.component';
import { ReportTemplateDeleteDialogComponent } from './report-template-delete-dialog.component';
import { reportTemplateRoute } from './report-template.route';

@NgModule({
  imports: [SpringSharedModule, RouterModule.forChild(reportTemplateRoute)],
  declarations: [
    ReportTemplateComponent,
    ReportTemplateDetailComponent,
    ReportTemplateUpdateComponent,
    ReportTemplateDeleteDialogComponent,
  ],
  entryComponents: [ReportTemplateDeleteDialogComponent],
})
export class SpringReportTemplateModule {}
