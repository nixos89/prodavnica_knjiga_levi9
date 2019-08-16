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

  public getBooksFilter(categories: number[]): Observable<any> {
    let categoryIdsSet = new Set<number>();
    categories.forEach(x => categoryIdsSet.add(x));

    let categoriesStr: string[] = [];
    categoryIdsSet.forEach(x => categoriesStr.push(x.toString()));
    console.log("categoriesStr: ", categoriesStr);

    this.httpParams = new HttpParams({
      fromObject: { id: categoriesStr }
    });
    categoryIdsSet.clear();
    categoriesStr = [];

    return this.http.get(
      environment.url + "api/books/getBooksFilter",
      {
        params: this.httpParams
      }
    );
  }
}
