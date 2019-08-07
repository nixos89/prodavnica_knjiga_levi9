import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { BookDetailsComponent } from './book-details/book-details.component';
import { FormsModule } from "@angular/forms";

@NgModule({
  declarations: [BookDetailsComponent],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [HttpClientModule]
})
export class BookModule { 

    

}