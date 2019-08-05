import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AddBookComponent } from './admin-management/add-book/add-book.component';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { CoreModule } from './core/core.module';
import { NavbarComponent } from './elements/navbar/navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    AddBookComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, CoreModule, BrowserAnimationsModule, ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
