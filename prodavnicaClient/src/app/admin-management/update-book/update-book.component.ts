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

  constructor(
    private adminService: AdminManagementService,
    private bookService: BookService,
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private router: Router
  ) {}

  ngOnInit() {
    this.getBookInfo();
  }

  getBookInfo() {
    this.route.params.subscribe(params => {
      if (params["idBook"]) {
        this.bookService.getBookInfo(params["idBook"]).subscribe(
          response => {
            this.bookData = response;
          },
          error => {
            this.toastr.error("Failed to get info about book");
          }
        );
      }
    });
  }

  onUpdateBook() {
    this.adminService.addBook(this.bookData).subscribe(
      response => {
        this.toastr.success("Successfuly updated book");
        this.router.navigate(["/"]);
      },
      error => {
        this.toastr.error("Failed to update book");
      }
    );
  }

  onChangeAuthorsData() {}

  onChangeCategoriesData() {}
}
