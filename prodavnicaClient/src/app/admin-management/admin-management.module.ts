import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddBookComponent } from './add-book/add-book.component';
import { UpdateBookComponent } from './update-book/update-book.component';



@NgModule({
  declarations: [AddBookComponent, UpdateBookComponent],
  imports: [
    CommonModule
  ]
})
export class AdminManagementModule { }
