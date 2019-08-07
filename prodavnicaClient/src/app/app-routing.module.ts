import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";

const routes: Routes = [
  {
    path: "",
    loadChildren:
      "./book/book.module#BookModule"
  },
  
  {
    path: "admin",
    loadChildren:
      "./admin-management/admin-management.module#AdminManagementModule"
  } 
 


];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
