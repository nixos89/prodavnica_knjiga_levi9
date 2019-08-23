import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment.prod';
import {OrderList} from '../models/orderList.model';
import {OrderItem} from '../models/orderItem.model';

const ORDER_ITEMS = 'orderItems';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  public orderItems: OrderItem[];

  constructor(private http: HttpClient) { }

  public orderBook(orderList: OrderList): Observable<any>{
    return this.http.post(environment.url + 'api/orders', orderList);
  }

  public saveOrderItems(orderItems: OrderItem[]) {
    window.sessionStorage.removeItem(ORDER_ITEMS);
    window.sessionStorage.setItem(ORDER_ITEMS, JSON.stringify(orderItems));
  }

  public getOrderItems(): OrderItem[] {
    this.orderItems = [];
    if (sessionStorage.getItem(ORDER_ITEMS)) {
      this.orderItems = JSON.parse(sessionStorage.getItem(ORDER_ITEMS));
    }
    return this.orderItems;
  }

  public getProcessedOrders(): Observable<any> {
    return this.http.get(environment.url + 'api/orders');
  }

  
  public getProcessedOrdersPDF(): Observable<any> {
    let httpHeaders  = new HttpHeaders()
      .set('Accept', 'application/pdf')  
      .set('Content-type', 'application/pdf');         
    return this.http.get(environment.url + 'api/orders/pdf', { headers: httpHeaders, responseType: 'blob' });
  }

}
