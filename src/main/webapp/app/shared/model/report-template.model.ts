import { Moment } from 'moment';

export interface IReportTemplate {
  id?: number;
  templateId?: string;
  templateName?: string;
  templateFileName?: string;
  templateFileContentType?: string;
  templateFile?: any;
  effectiveDt?: Moment;
  expiryDt?: Moment;
  status?: string;
  createdBy?: string;
  createdDt?: Moment;
  updatedBy?: string;
  updatedDt?: Moment;
  version?: number;
}

export class ReportTemplate implements IReportTemplate {
  constructor(
    public id?: number,
    public templateId?: string,
    public templateName?: string,
    public templateFileName?: string,
    public templateFileContentType?: string,
    public templateFile?: any,
    public effectiveDt?: Moment,
    public expiryDt?: Moment,
    public status?: string,
    public createdBy?: string,
    public createdDt?: Moment,
    public updatedBy?: string,
    public updatedDt?: Moment,
    public version?: number
  ) {}
}
