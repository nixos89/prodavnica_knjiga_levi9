import {Book} from './book.model';

export class OrderItem{
  book: Book;
  quantity: number;
  amount: number ;

  constructor(){
    this.book = new Book();
    this.quantity = 1;
    this.amount = 0;
  }
}
