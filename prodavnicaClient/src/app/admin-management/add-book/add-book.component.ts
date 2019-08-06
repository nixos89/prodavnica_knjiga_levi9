import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/core/models/book.model';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
  bookToInsert: Book;

  constructor() {

  }

  ngOnInit() {

  }

  insertBook() {
    
  }


}
