import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { BookDetailsComponent } from './book-details/book-details.component';
import { FormsModule } from "@angular/forms";
import { BookRoutingModule } from './book-routing.module';
import { HomepageComponent } from './homepage/homepage.component';

@NgModule({
  declarations: [BookDetailsComponent, HomepageComponent],
  imports: [    
    CommonModule,
    FormsModule,
    BookRoutingModule
  ],
  exports: [HttpClientModule]
})
export class BookModule { 

    

}