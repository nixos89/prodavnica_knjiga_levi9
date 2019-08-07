import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddBookComponent } from './add-book/add-book.component';
import { UpdateBookComponent } from './update-book/update-book.component';
import { AdminManagementRoutingModule } from './admin-management-routing.module';
import { FormsModule } from '@angular/forms';



@NgModule({
  declarations: [AddBookComponent, UpdateBookComponent],
  imports: [
    CommonModule, FormsModule, AdminManagementRoutingModule
  ]
})
export class AdminManagementModule { }
