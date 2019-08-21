import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { AddBookComponent } from "./add-book/add-book.component";
import { UpdateBookComponent } from "./update-book/update-book.component";
import {AddCategoryComponent} from './add-category/add-category.component';
import { ProcessedOrdersComponent } from './processed-orders/processed-orders.component';

const routes: Routes = [
  { path: "add-book", component: AddBookComponent },
  { path: "update-book/:idBook", component: UpdateBookComponent },
  { path: "add-category/", component: AddCategoryComponent },
  { path: "processed-orders", component: ProcessedOrdersComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminManagementRoutingModule {}
