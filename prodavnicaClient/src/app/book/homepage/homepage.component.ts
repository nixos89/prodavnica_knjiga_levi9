import { Component, OnInit, SimpleChange, SimpleChanges } from "@angular/core";
import { Book } from "src/app/core/models/book.model";
import { BookService } from "src/app/core/services/book.service";
import { AuthorService } from "src/app/core/services/author.service";
import { CategoryService } from "src/app/core/services/category.service";
import { ToastrService } from "ngx-toastr";
import { Router, ActivatedRoute } from "@angular/router";
import { BookInfo } from "src/app/core/models/bookInfo.model";
import { AuthorInfo } from "src/app/core/models/authorInfo.model";
import { CategoryInfo } from "src/app/core/models/categoryInfo.model";
import { KeyValue } from "@angular/common";
import { Category } from "src/app/core/models/category.model";
import { OrderService } from "../../core/services/order.service";
import { AddOrder } from "../../core/models/addOrder.model";
import { OrderList } from "../../core/models/orderList.model";
import { OrderItem } from "src/app/core/models/orderItem.model";

@Component({
  selector: "app-homepage",
  templateUrl: "./homepage.component.html",
  styleUrls: ["./homepage.component.css"],
  providers: [BookService, AuthorService, CategoryService]
})
export class HomepageComponent implements OnInit {
  bookData: BookInfo = new BookInfo();
  top10Books: Book[]; // = getTop10Books();
  authorData: AuthorInfo = new AuthorInfo();
  categoryData: CategoryInfo = new CategoryInfo();
  newBooksForCat: Category[] = [];
  activeAddToCart: Number[] = [];
  orderItems: OrderItem[] = [];
  search = "";
  searchActive = false;
  categoryActive = false;

  // TODO: finish method for sorting Categories
  sortCategories = (
    a: KeyValue<Category, string>,
    b: KeyValue<Category, string>
  ): Category => {
    return new Category();
  };

  constructor(
    private bookService: BookService,
    private authorService: AuthorService,
    private categoryService: CategoryService,
    private toastr: ToastrService,
    private router: Router,
    private route: ActivatedRoute,
    private orderService: OrderService
  ) {}

  ngOnInit() {
    // this.getTop10Books();
    this.getAllBooks();
    this.getAllCategories();
  }

  // getTop10Books() {
  //   this.bookService.getTop10Books()
  //     .subscribe(bookData => {
  //       this.bookData = bookData;
  //     },
  //       err => {
  //         console.log(err);
  //       }
  //     )
  // }

  getAllBooks() {
    this.bookService.getAllBooks().subscribe(
      response => {
        this.bookData = response;
        for (let book of response.books) {
          this.activeAddToCart.push(book.bookId);
        }
        this.getBooksInCart();
      },
      error => {
        this.toastr.error("Failed to get authors");
      }
    );
  }

  getAllCategories() {
    this.categoryService.getAll().subscribe(
      response => {
        this.categoryData = response; //.categories;
      },
      error => {
        this.toastr.error("Failed to get categories");
      }
    );
  }

  getAllBooksFromCategories() {
    this.newBooksForCat = this.categoryData.categories
      .filter(x => x.checked)
      .map(x => x);

    this.newBooksForCat.forEach(x => {});

    let catIds: number[] = [];
    this.newBooksForCat.forEach(x => {
      catIds.push(x.categoryId);
    });

    console.log("catIds: ", catIds);

    this.categoryService.getAllBooksFromCategories(catIds).subscribe(
      response => {
        this.categoryActive = true;
        let bookInfo: BookInfo = new BookInfo();
        if (this.searchActive) {
          for (let book of this.bookData.books) {
            for (let catBook of response.books) {
              if (book.bookId == catBook.bookId) {
                bookInfo.books.push(book);
                this.bookData = bookInfo;
              }
            }
          }
        } else {
          this.bookData = response;
        }
      },
      error => {
        this.toastr.error("Failed to get books for selected categories");
      }
    );
  }

  buyBook(book: Book) {
    let orderItem: AddOrder = new AddOrder();
    orderItem.bookId = book.bookId;
    orderItem.amount = 1;
    let orderList: OrderList = new OrderList();
    orderList.total = book.price;
    orderList.orders.push(orderItem);
    this.orderService.orderBook(orderList).subscribe(
      data => {
        this.toastr.success("success");
      },
      error => {
        this.toastr.error(error.error.message);
      }
    );
  }

  onAddToCart(book) {
    let orderItem: OrderItem = new OrderItem();
    for (let activeCart of this.activeAddToCart) {
      if (activeCart == book.bookId) {
        this.activeAddToCart.splice(
          this.activeAddToCart.indexOf(book.bookId),
          1
        );
      }
    }
    orderItem.book = book;
    this.orderItems.push(orderItem);
    window.sessionStorage.setItem(
      "orderItems",
      JSON.stringify(this.orderItems)
    );
    this.toastr.success("Book added to cart");
  }

  getBooksInCart() {
    this.orderItems = JSON.parse(window.sessionStorage.getItem("orderItems"));
    if (this.orderItems == null) {
      this.orderItems = [];
    } else {
      for (let orderItem of this.orderItems) {
        if (this.activeAddToCart.includes(orderItem.book.bookId)) {
          this.activeAddToCart.splice(
            this.activeAddToCart.indexOf(orderItem.book.bookId),
            1
          );
        }
      }
    }
  }

  onSearch() {
    setTimeout(() => {
      console.log(this.search);
      if (this.search) {
        this.searchActive = true;
        this.bookService.searchBooks(this.search).subscribe(response => {
          if (this.categoryActive) {
            let bookInfo: BookInfo = new BookInfo();
            for (let book of this.bookData.books) {
              for (let searchBook of response.books) {
                if (book.bookId == searchBook.bookId) {
                  bookInfo.books.push(book);
                  this.bookData = bookInfo;
                }
              }
            }
          } else {
            this.bookData = response;
          }
        });
      } else {
        this.searchActive = false;
        if (this.categoryActive) {
          this.getAllBooksFromCategories();
        } else {
          this.getAllBooks();
        }
      }
    }, 500);
  }
}
