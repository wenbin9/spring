import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IReportTemplate, ReportTemplate } from 'app/shared/model/report-template.model';
import { ReportTemplateService } from './report-template.service';
import { ReportTemplateComponent } from './report-template.component';
import { ReportTemplateDetailComponent } from './report-template-detail.component';
import { ReportTemplateUpdateComponent } from './report-template-update.component';

@Injectable({ providedIn: 'root' })
export class ReportTemplateResolve implements Resolve<IReportTemplate> {
  constructor(private service: ReportTemplateService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IReportTemplate> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((reportTemplate: HttpResponse<ReportTemplate>) => {
          if (reportTemplate.body) {
            return of(reportTemplate.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ReportTemplate());
  }
}

export const reportTemplateRoute: Routes = [
  {
    path: '',
    component: ReportTemplateComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'springApp.reportTemplate.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ReportTemplateDetailComponent,
    resolve: {
      reportTemplate: ReportTemplateResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'springApp.reportTemplate.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ReportTemplateUpdateComponent,
    resolve: {
      reportTemplate: ReportTemplateResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'springApp.reportTemplate.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ReportTemplateUpdateComponent,
    resolve: {
      reportTemplate: ReportTemplateResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'springApp.reportTemplate.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
