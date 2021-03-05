import { Moment } from 'moment';

export interface IReport {
  id?: number;
  reportName?: string;
  reportSize?: number;
  createdBy?: string;
  createdDt?: Moment;
  status?: string;
}

export class Report implements IReport {
  constructor(
    public id?: number,
    public reportName?: string,
    public reportSize?: number,
    public createdBy?: string,
    public createdDt?: Moment,
    public status?: string
  ) {}
}
