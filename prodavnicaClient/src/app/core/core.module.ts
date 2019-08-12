import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HttpParams } from '@angular/common/http';

@NgModule({
  declarations: [],
  imports: [
    CommonModule    
  ],
  exports: [HttpClientModule],
  providers: [HttpParams]
})
export class CoreModule { }
