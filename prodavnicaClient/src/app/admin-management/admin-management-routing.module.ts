import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { AddBookComponent } from "./add-book/add-book.component";
import { UpdateBookComponent } from "./update-book/update-book.component";

const routes: Routes = [
  { path: "add-book", component: AddBookComponent },
  { path: "update-book/:idBook", component: UpdateBookComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminManagementRoutingModule {}
