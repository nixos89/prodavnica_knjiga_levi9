import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import {
  HttpClientModule,
  HttpParams,
  HTTP_INTERCEPTORS
} from "@angular/common/http";
import { HttpTokenInterceptor } from "./interceptor/httpTokenInterceptor";

@NgModule({
  declarations: [],
  imports: [CommonModule],
  exports: [HttpClientModule],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpTokenInterceptor, multi: true },
    HttpParams,
    Set
  ]
})
export class CoreModule {}
