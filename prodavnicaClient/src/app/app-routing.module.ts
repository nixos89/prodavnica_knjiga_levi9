import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { IsAdminGuard } from './core/guards/isAdmin.guard';

const routes: Routes = [
  {
    path: "",
    loadChildren: "./book/book.module#BookModule"
  },
  {
    path: "",
    loadChildren: "./authentication/authentication.module#AuthenticationModule"
  },
  {
    path: "admin",
    loadChildren:
      "./admin-management/admin-management.module#AdminManagementModule",
      canActivate : [IsAdminGuard]
  },
  {
    path: "categories",
    loadChildren: "./category/category.module#CategoryModule"
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
