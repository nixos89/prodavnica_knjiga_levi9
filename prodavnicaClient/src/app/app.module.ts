import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { CoreModule } from './core/core.module';
import { NavbarComponent } from './elements/navbar/navbar.component';
import {BookModule} from './book/book.module';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, CoreModule, BrowserAnimationsModule, ToastrModule.forRoot(), BookModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
