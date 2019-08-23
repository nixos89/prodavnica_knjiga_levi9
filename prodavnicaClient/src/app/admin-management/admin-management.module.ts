import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { AddBookComponent } from "./add-book/add-book.component";
import { UpdateBookComponent } from "./update-book/update-book.component";
import { AdminManagementRoutingModule } from "./admin-management-routing.module";
import { FormsModule } from "@angular/forms";
import { ProcessedOrdersComponent } from './processed-orders/processed-orders.component';

@NgModule({
  declarations: [AddBookComponent, UpdateBookComponent, ProcessedOrdersComponent],
  imports: [CommonModule, FormsModule, AdminManagementRoutingModule]
})
export class AdminManagementModule {}
