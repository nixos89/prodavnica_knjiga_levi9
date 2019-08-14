import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment.prod';
import {OrderList} from '../models/orderList.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  public orderBook(orderList: OrderList): Observable<any>{
    return this.http.post(environment.url + 'api/orders', orderList);
  }

}
