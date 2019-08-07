import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/core/models/book.model';
import { AdminManagementService } from 'src/app/core/services/admin-management.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
  bookData: Book = new Book();

  constructor(private adminService: AdminManagementService, private toastr: ToastrService, private router: Router) {
  }

  ngOnInit() {
  }

  onAddBook() {
    this.adminService.addBook(this.bookData).subscribe(response => {
      this.toastr.success("Successfuly added book");
      this.router.navigate(["/"]);
    }, error => {
      this.toastr.error("Failed to add book");
    })
  }


}
