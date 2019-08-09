import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Category } from '../models/category.model';

@Injectable({
  providedIn: "root"
})
export class CategoryService {
  constructor(private http: HttpClient) {}

  public getAll(): Observable<any> {
    return this.http.get(environment.url + "api/categories");
  }
  
  public getAllBooksFromCategories(ids:Category[]): Observable<any>{
    return this.http.get(environment.url + "api/categories/getAllBooksFromCategories")
  }
}
