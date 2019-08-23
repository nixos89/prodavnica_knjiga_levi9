import { Book } from './book.model';

export class OrderItemReport {
    orderItemId?: number;
    orderId: number;
    bookDTO: Book;
    orderedAmount: number;
    totalOrderedItemPrice: number;

    constructor() {
        this.orderId = 0;
        this.bookDTO = new Book();
        this.orderedAmount = 0;
        this.totalOrderedItemPrice = 0;
     }

}