import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CategoryPageComponent} from './category-page/category-page.component';
import { IsAdminGuard } from '../core/guards/isAdmin.guard';

const routes: Routes = [
    { path: '', component: CategoryPageComponent, canActivate: [IsAdminGuard] }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CategoryRoutingModule { }
