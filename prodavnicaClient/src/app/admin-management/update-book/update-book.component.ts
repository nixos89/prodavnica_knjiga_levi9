import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Book } from 'src/app/core/models/book.model';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {
  
  bookToUpdate: Book;
  apiUrl: string = 'http://localhost:4200';

  constructor(private _http: HttpClient) {
      
  }

  ngOnInit() {    
    
  }

}
