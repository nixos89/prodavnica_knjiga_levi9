import {AddOrder} from './addOrder.model';

export class OrderList {

  orders: AddOrder[] ;
  total: number;

  constructor() {
    this.orders = [];
    this.total = 0;
  }

}
