import { CategoryService } from "src/app/core/services/category.service";
import { AuthorService } from "src/app/core/services/author.service";
import { Component, OnInit } from "@angular/core";
import { Book } from "src/app/core/models/book.model";
import { AdminManagementService } from "src/app/core/services/admin-management.service";
import { ToastrService } from "ngx-toastr";
import { Router, ActivatedRoute } from "@angular/router";
import { BookService } from "src/app/core/services/book.service";
import { AuthorInfo } from "src/app/core/models/authorInfo.model";
import { CategoryInfo } from "src/app/core/models/categoryInfo.model";

@Component({
  selector: "app-update-book",
  templateUrl: "./update-book.component.html",
  styleUrls: ["./update-book.component.css"]
})
export class UpdateBookComponent implements OnInit {
  bookData: Book = new Book();
  authorData: AuthorInfo = new AuthorInfo();
  categoryData: CategoryInfo = new CategoryInfo();
  authorIds: number[] = [];
  categoryIds: number[] = [];
  idBook: number;
  active;

  constructor(
    private adminService: AdminManagementService,
    private authorService: AuthorService,
    private categoryService: CategoryService,
    private bookService: BookService,
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) {}

  ngOnInit() {
    this.getAllAuthors();
    this.getAllCategories();
    this.getBookInfo();
  }

  getBookInfo() {
    this.route.params.subscribe(params => {
      if (params["idBook"]) {
        this.idBook = params["idBook"];
        this.bookService.getBookInfo(this.idBook).subscribe(
          response => {
            for (var author of response.authors) {
              this.authorIds.push(author.authorId);
            }
            for (var category of response.categories) {
              this.categoryIds.push(category.categoryId);
            }
            this.bookData.bookId = response.bookId;
            this.bookData.amount = response.amount;
            this.bookData.deleted = !response.deleted;
            this.bookData.name = response.name;
            this.bookData.price = response.price;
            this.bookData.authorIds = this.authorIds;
            this.bookData.categoryIds = this.categoryIds;
            this.active = this.bookData.deleted ? "Active" : "Deactivate"
          },
          error => {
            this.toastr.error("Failed to get info about book");
          }
        );
      }
    });
  }

  onUpdateBook() {
    this.bookData.deleted = !this.bookData.deleted ? true : false;
    this.active = !this.bookData.deleted ? "Active" : "Deactivate"
    this.adminService.updateBook(this.bookData, this.idBook).subscribe(
      response => {
        this.toastr.success("Successfuly updated book");
        this.router.navigate(["/"]);
      },
      error => {
        this.toastr.error("Failed to update book");
      }
    );
  }

  getAllAuthors() {
    this.authorService.getAll().subscribe(
      response => {
        this.authorData = response;
      },
      error => {
        this.toastr.error("Failed to get authors");
      }
    );
  }

  getAllCategories() {
    this.categoryService.getAll().subscribe(
      response => {
        this.categoryData = response;
      },
      error => {
        this.toastr.error("Failed to get categories");
      }
    );
  }

  onChangeAuthor(authorId) {
    this.bookData.authorIds.push(authorId.value);
    this.bookData.authorIds.pop();
  }

  onChangeCategory(categoryId) {
    this.bookData.categoryIds.push(categoryId.value);
    this.bookData.categoryIds.pop();
  }

  onChangeStatus(deleted){
    this.active = deleted ? "Active" : "Deactive"
  }
}
