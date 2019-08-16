import { Component, OnInit } from '@angular/core';
import { Book } from '../../core/models/book.model';
import { BookInfo } from '../../core/models/bookInfo.model';
import { BookService } from '../../core/services/book.service';
import { ToastrService } from 'ngx-toastr';
import { OrderItem } from '../../core/models/orderItem.model';
import { ConstructorDepError } from '@angular/compiler-cli/src/ngtsc/annotations/src/util';
import { OrderBook } from '../../core/models/orderBook.model';
import { OrderService } from '../../core/services/order.service';
import { OrderList } from '../../core/models/orderList.model';
import { AddOrder } from '../../core/models/addOrder.model';


const GET_ORDERS = 'orderItems';


@Component({
  selector: 'app-order-cart',
  templateUrl: './order-cart.component.html',
  styleUrls: ['./order-cart.component.css']
})
export class OrderCartComponent implements OnInit {

  bookData: BookInfo = new BookInfo();
  orderItems: OrderItem[] = [];
  order: OrderBook = new OrderBook();
  orderList: OrderList = new OrderList();
  message:string;
  succesOrder :boolean;
  errorOrder: boolean;


  constructor(private bookService: BookService, private toastr: ToastrService, private orderService: OrderService) { }

  ngOnInit() {
    this.getOrderItems();
  }


  getAllOrderMock() {
    this.bookService.getAllBooks().subscribe(
      response => {
        this.bookData = response;
        for (const i of this.bookData.books) {
          const ord1: OrderItem = new OrderItem();
          ord1.book = i;
          ord1.quantity = 1;
          ord1.amount = i.price;
          this.order.total += ord1.amount;
          this.orderItems.push(ord1);
        }
      },
      error => {
        this.toastr.error('Error');
      }
    );
  }

  minusQuantity(ord: OrderItem) {
    if (ord.quantity > 1) {
      ord.quantity -= 1;
      ord.amount = ord.quantity * ord.book.price;
      this.order.total -= ord.book.price;
      this.orderService.saveOrderItems(this.orderItems);
    }
  }

  plusQuantity(ord: OrderItem) {
    ord.quantity += 1;
    ord.amount = ord.quantity * ord.book.price;
    this.order.total += ord.book.price;
    this.orderService.saveOrderItems(this.orderItems);
  }

  deleteOrder(i: number) {
    this.orderItems.splice(i, 1);
    this.getTotal();
    this.toastr.success('Order item is removed from cart');
    this.orderService.saveOrderItems(this.orderItems);
  }

  getTotal() {
    this.order.total = 0;
    for (let i of this.orderItems) {
      this.order.total += i.amount;
    }
  }

  deleteAllItems() {
    this.orderItems = new Array();
    this.order.total = 0;
    this.toastr.success('The cart is empty');
    this.orderService.saveOrderItems(this.orderItems);
  }

  getOrderItems() {
    this.orderItems = this.orderService.getOrderItems();
    this.getTotal();
  }

  makePurchase() {
    let selectedOrderList: OrderList = new OrderList();
    for(let ord of this.orderItems) {
      let addOrder: AddOrder = new AddOrder();
      addOrder.amount = ord.quantity;
      addOrder.bookId = ord.book.bookId;
      selectedOrderList.orders.push(addOrder);
    }
    selectedOrderList.total = this.order.total;
    console.log('selectedOrderList:', selectedOrderList);


    this.orderService.orderBook(selectedOrderList).subscribe(
      response => {
        this.message = "Purchase has been successfuly completed!\nID of this order: #"+ response.orderId;
        this.orderItems = new Array();
        this.order.total = 0;
        this.orderService.saveOrderItems(this.orderItems);
        this.succesOrder = true;
        this.errorOrder = false;
      },
      error => {
        this.message = "You have selected more books than it's possible!\n" + error.error.message;
        this.succesOrder = false;
        this.errorOrder = true;
      }
    );
  }

}
