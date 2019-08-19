import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: "root"
})
export class BookService {
  constructor(private http: HttpClient, private httpParams: HttpParams) {}

  public getBookInfo(idBook: number): Observable<any> {
    return this.http.get(environment.url + "api/books/" + idBook);
  }

  public getAllBooks(): Observable<any> {
    return this.http.get(environment.url + "api/books");
  }

  public getTopSellingBooks(): Observable<any> {
    return this.http.get(environment.url + "api/books/topSellingBooksLimit");
  }
  
  public getBooksFilter(categories: number[], searchString): Observable<any> {
    let categoryIdsSet = new Set<number>();
    categories.forEach(x => categoryIdsSet.add(x));
    let categoriesStr: string[] = [];
    categoryIdsSet.forEach(x => categoriesStr.push(x.toString()));
    this.httpParams = new HttpParams({
      fromObject: { id: categoriesStr, search: searchString }
    });
    return this.http.get(environment.url + "api/books/getBooksFilter", {
      params: this.httpParams
    });
  }
}
