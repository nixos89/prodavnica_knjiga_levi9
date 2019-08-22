export class Book {
  bookId?: number;
  name: string;
  amount: number;
  price: number;
  deleted?: boolean;
  categoryIds?: number[];
  authorIds?: number[];

  constructor() {
    this.bookId;
    this.name = "";
    this.amount;
    this.price;
    this.deleted=true;
    this.categoryIds = [];
    this.authorIds = [];
  }
}
