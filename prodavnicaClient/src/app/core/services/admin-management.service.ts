import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Book } from "../models/book.model";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: "root"
})
export class AdminManagementService {
  constructor(private http: HttpClient) {}

  public addBook(book: Book): Observable<any> {
    return this.http.post(environment.url + "api/book/add", book);
  }

  public updateBook(book: Book, idBook: number): Observable<any> {
    return this.http.put(environment.url + "api/book/update" + idBook, book);
  }
}
