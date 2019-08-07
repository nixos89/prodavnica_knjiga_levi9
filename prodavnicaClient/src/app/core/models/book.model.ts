export class Book {
  bookId?: Number;
  name: string;
  amount: number;
  price: number;
  isDeleted: boolean;
  categories?: [];
  authors?: [];

  constructor() {
    this.bookId;
    this.name = "";
    this.amount;
    this.price;
    this.isDeleted;
    this.categories = [];
    this.authors = [];
  }
}
