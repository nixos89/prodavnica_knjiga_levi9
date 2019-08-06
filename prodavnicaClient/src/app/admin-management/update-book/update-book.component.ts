import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/core/models/book.model';
import { AdminManagementService } from 'src/app/core/services/admin-management.service';
import { ToastrService } from 'ngx-toastr';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {
  updateBook: Book = new Book();

  constructor(private adminService: AdminManagementService, private route: ActivatedRoute,private toastr: ToastrService, private router: Router) {
  }

  ngOnInit() {
    this.getBookInfo();  
  }

  getBookInfo(){
    this.route.params.subscribe(params => {
      if (params["idBook"]) {
        this.adminService.getBookInfo(params["idBook"]).subscribe(response =>{
          this.updateBook = response;
        }, error => {
          this.toastr.error("Failed to get info about book");
        });
      }
    }); 
   
  }

  onUpdateBook(){
    this.adminService.addBook(this.updateBook).subscribe(response =>{
      this.toastr.success("Successfuly updated book");
      this.router.navigate(["/"]);
    }, error =>{
      this.toastr.error("Failed to update book");
    })
  }

}
