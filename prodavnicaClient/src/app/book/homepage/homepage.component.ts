import { Component, OnInit } from "@angular/core";
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
import { OrderItem } from "src/app/core/models/orderItem.model";

@Component({
  selector: "app-homepage",
  templateUrl: "./homepage.component.html",
  styleUrls: ["./homepage.component.css"],
  providers: [BookService, AuthorService, CategoryService]
})
export class HomepageComponent implements OnInit {
  top10Books: Book[]; // = getTop10Books();
  catIds: number[] = [];
  bookData: BookInfo = new BookInfo();
  authorData: AuthorInfo = new AuthorInfo();
  categoryData: CategoryInfo = new CategoryInfo();
  activeAddToCart: Number[] = [];
  orderItems: OrderItem[] = [];

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
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    // this.getTop10Books();
    this.getAllBooks();
    this.getAllCategories();
  }

  // getTop10Books(): void {
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
        console.log(
          "response.categories[0].categoryId: " +
            response.categories[0].categoryId
        );
      },
      error => {
        this.toastr.error("Failed to get categories");
      }
    );
  }

  getAllBooksFromCategories(id: number) {
    this.catIds.push(id);

    // this.route.queryParams.subscribe((catIds) =>

    this.categoryService.getAllBooksFromCategories(this.catIds).subscribe(
      response => {
        this.bookData = response; //.books;
        // console.log('response.books[0].name: ' + response.books[0].name);
      },
      error => {
        this.toastr.error("Failed to get books for selected categories");
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
}
