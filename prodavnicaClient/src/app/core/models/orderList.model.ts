import {AddOrder} from './addOrder.model';

export class OrderList {

  orders: AddOrder[] ;
  total: number;
  username: string;

  constructor() {
    this.orders = [];
    this.total = 0;
    this.username = "";
  }

}
