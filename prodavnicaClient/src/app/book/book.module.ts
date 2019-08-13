import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { BookDetailsComponent } from './book-details/book-details.component';
import { FormsModule } from "@angular/forms";
import { BookRoutingModule } from './book-routing.module';
import { HomepageComponent } from './homepage/homepage.component';
import { OrderCartComponent } from './order-cart/order-cart.component';

@NgModule({
  declarations: [BookDetailsComponent, HomepageComponent, OrderCartComponent],
  imports: [    
    CommonModule,
    FormsModule,
    BookRoutingModule
  ],
  exports: [HttpClientModule]
})
export class BookModule { 

    

}