import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

const routes: Routes = [
  {
    path: "admin",
    loadChildren:
      "./admin-management/admin-management.module#AdminManagementModule"
  }, 
  {
    path: "",
    loadChildren:
      "./book/book.module#BookModule"
  }


];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
