import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: "root"
})
export class BookService {
  constructor(private http: HttpClient) {}

  public getBookInfo(idBook: number): Observable<any> {
    return this.http.get(environment.url + "api/books/" + idBook);
  }

  public getAllBooks(): Observable<any> {
    return this.http.get(environment.url + "api/books");
  }

  public getTopSellingBooks(): Observable<any> {
    return this.http.get(environment.url + "api/books/topSellingBooksLimit");
  }
}
