import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { BookDetailsComponent } from './book-details/book-details.component';

@NgModule({
  declarations: [BookDetailsComponent],
  imports: [
    CommonModule
  ],
  exports: [HttpClientModule]
})
export class BookModule { 

    

}