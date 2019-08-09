import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CategoryPageComponent } from './category-page/category-page.component';
import {CategoryRoutingModule} from './category-routing.module';



@NgModule({
  declarations: [CategoryPageComponent],
  imports: [
    CommonModule, FormsModule, CategoryRoutingModule
  ]
})
export class CategoryModule { }
