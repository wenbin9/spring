import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'report',
        loadChildren: () => import('./report/report.module').then(m => m.SpringReportModule),
      },
      {
        path: 'report-template',
        loadChildren: () => import('./report-template/report-template.module').then(m => m.SpringReportTemplateModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class SpringEntityModule {}
