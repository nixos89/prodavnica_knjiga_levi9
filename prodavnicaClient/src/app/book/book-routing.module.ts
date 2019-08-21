import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HomepageComponent } from "./homepage/homepage.component";
import {OrderCartComponent} from './order-cart/order-cart.component';
import {IsUserGuard} from '../core/guards/isUser.guard';

const routes: Routes = [
    { path: "", component: HomepageComponent },
  {path: 'cart', component: OrderCartComponent, canActivate: [IsUserGuard]}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class BookRoutingModule { }
