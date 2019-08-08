import { Component, OnInit } from "@angular/core";
import { Book } from "src/app/core/models/book.model";
import { AdminManagementService } from "src/app/core/services/admin-management.service";
import { Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { AuthorService } from "src/app/core/services/author.service";
import { CategoryService } from "src/app/core/services/category.service";
import { AuthorInfo } from "src/app/core/models/authorInfo.model";
import { CategoryInfo } from "src/app/core/models/categoryInfo.model";

@Component({
  selector: "app-add-book",
  templateUrl: "./add-book.component.html",
  styleUrls: ["./add-book.component.css"]
})
export class AddBookComponent implements OnInit {
  bookData: Book = new Book();
  authorData: AuthorInfo = new AuthorInfo();
  categoryData: CategoryInfo = new CategoryInfo();

  constructor(
    private adminService: AdminManagementService,
    private authorService: AuthorService,
    private categoryService: CategoryService,
    private toastr: ToastrService,
    private router: Router
  ) {}

  ngOnInit() {
    this.getAllAuthors();
    this.getAllCategories();
  }

  onAddBook() {
    this.adminService.addBook(this.bookData).subscribe(
      response => {
        this.toastr.success("Successfuly added book");
        this.router.navigate(["/"]);
      },
      error => {
        this.toastr.error("Failed to add book");
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

  onChangeAuthorsData() {}

  onChangeCategoriesData() {}

  onChange() {
    console.log("aa");
    console.log(this.bookData.isDeleted);
  }
}
