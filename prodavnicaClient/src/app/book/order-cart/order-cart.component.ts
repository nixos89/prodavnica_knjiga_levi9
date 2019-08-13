import { Component, OnInit } from '@angular/core';
import {Book} from '../../core/models/book.model';
import {BookInfo} from '../../core/models/bookInfo.model';
import {BookService} from '../../core/services/book.service';
import {ToastrService} from 'ngx-toastr';
import {OrderItem} from '../../core/models/orderItem.model';
import {ConstructorDepError} from '@angular/compiler-cli/src/ngtsc/annotations/src/util';
import {OrderBook} from '../../core/models/orderBook.model';


const GET_ORDERS = 'orderItems';


@Component({
  selector: 'app-order-cart',
  templateUrl: './order-cart.component.html',
  styleUrls: ['./order-cart.component.css']
})
export class OrderCartComponent implements OnInit {



  bookData : BookInfo = new BookInfo();
  orderItems : OrderItem[] = [];
  order : OrderBook = new OrderBook();
  constructor(private bookService:BookService,private toastr: ToastrService) { }

  ngOnInit() {
    this.getAllOrderMock();
    // this.getOrderItems() // Get from sessionStorage
  }


  getAllOrderMock() {
    this.bookService.getAllBooks().subscribe(
      response => {
        this.bookData = response;
        for( const i of this.bookData.books) {
          const ord1: OrderItem = new OrderItem();
          ord1.book = i;
          ord1.quantity = 1;
          ord1.amount = i.price;
          this.order.total += ord1.amount;
          this.orderItems.push(ord1);
        }
      },
      error => {
        this.toastr.error("Error");
      }
    );
  }

  minusQuantity(ord: OrderItem) {
    if (ord.quantity> 1) {
      ord.quantity -= 1;
      ord.amount = ord.quantity * ord.book.price;
      this.order.total -= ord.book.price;
      this.toastr.success('The quantity was reduced for Book ' + ord.book.name);

    }
    }

  plusQuantity(ord: OrderItem) {
    ord.quantity  += 1 ;
    ord.amount = ord.quantity * ord.book.price ;
    this.order.total += ord.book.price;
    this.toastr.success('The quantity is increased for Book ' + ord.book.name);
  }

  deleteOrder(i: number) {
  this.orderItems.splice(i, 1);
  this.getTotal();
  this.toastr.success('Order item is removed from cart');
  }

  getTotal(){
    this.order.total = 0 ;
    for (let i of this.orderItems){
      this.order.total +=i.amount;
    }
  }

  deleteAllItems() {
    this.orderItems = new Array();
    this.order.total = 0;
    this.toastr.success("The cart is empty");
  }
  getOrderItems(){
    if (sessionStorage.getItem(GET_ORDERS)) {
      this.orderItems = JSON.parse(sessionStorage.getItem(GET_ORDERS));
      this.getTotal();
    }
  }
}
