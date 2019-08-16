import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {OrderService} from '../../core/services/order.service';
import {log} from 'util';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  itemsNumber: number;

  constructor(public orderService: OrderService) { }

  ngOnInit() {
    this.itemsNumber = this.orderService.getOrderItems().length;
  }

}
