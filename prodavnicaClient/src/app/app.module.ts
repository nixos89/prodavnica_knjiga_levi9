import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { CoreModule } from './core/core.module';
import { NavbarComponent } from './elements/navbar/navbar.component';
import { HomepageComponent } from './book/homepage/homepage.component';
import { AddBookComponent } from './admin-management/add-book/add-book.component';

@NgModule({
  declarations: [
    AppComponent,
    AddBookComponent,
    NavbarComponent,
    HomepageComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, CoreModule, BrowserAnimationsModule, ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
