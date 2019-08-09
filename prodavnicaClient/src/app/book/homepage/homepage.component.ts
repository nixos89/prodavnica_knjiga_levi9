import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/core/models/book.model';
import { BookService } from 'src/app/core/services/book.service';
import { AuthorService } from 'src/app/core/services/author.service';
import { CategoryService } from 'src/app/core/services/category.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { BookInfo } from 'src/app/core/models/bookInfo.model';
import { AuthorInfo } from 'src/app/core/models/authorInfo.model';
import { CategoryInfo } from 'src/app/core/models/categoryInfo.model';
import { KeyValue } from '@angular/common';
import { Category } from 'src/app/core/models/category.model';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css'],
  providers: [BookService, AuthorService, CategoryService]
})
export class HomepageComponent implements OnInit {

  top10Books: Book[]; // = getTop10Books();
  bookData: BookInfo = new BookInfo();
  authorData: AuthorInfo = new AuthorInfo();
  categoryData: CategoryInfo = new CategoryInfo();

  // TODO: finish method for sorting Categories
  sortCategories = (a: KeyValue<Category, string>, b: KeyValue<Category, string>): Category => {
    return new Category();
  }

  constructor(
    private bookService: BookService,
    private authorService: AuthorService,
    private categoryService: CategoryService,
    private toastr: ToastrService,
    private router: Router) {

  }

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
      },
      error => {
        this.toastr.error("Failed to get authors");
      }
    );
  }

  getAllCategories() {
    this.categoryService.getAll().subscribe(
      response => {
        this.categoryData = response;//.categories;
        console.log('response.categories[0].categoryId: ' + response.categories[0].categoryId);        
      },
      error => {
        this.toastr.error("Failed to get categories");
      }
    );
  }

  getAllBooksFromCategories(ids:Category[]){
    this.categoryService.getAllBooksFromCategories(ids).subscribe(
      response => {
        this.bookData = response;//.books;
        // console.log('response.books[0].name: ' + response.books[0].name);        
      },
      error => {
        this.toastr.error("Failed to get books for selected categories");
      }
    );
  }

}
