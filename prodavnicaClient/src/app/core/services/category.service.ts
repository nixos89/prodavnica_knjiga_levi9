import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";

import { AddUpdateCategory } from "../models/addUpdateCategory.model";

@Injectable({
  providedIn: "root"
})
export class CategoryService {
  constructor(private http: HttpClient, private httpParams: HttpParams) {}

  public getAll(): Observable<any> {
    return this.http.get(environment.url + "api/categories");
  }

  updateCategory(updateCat: AddUpdateCategory, id: number): Observable<any> {
    return this.http.put(environment.url + "api/categories/" + id, updateCat);
  }

  deleteCategory(id: number): Observable<any> {
    return this.http.delete(environment.url + "api/categories/" + id);
  }
  addCategory(addCat: AddUpdateCategory): Observable<any> {
    return this.http.post(environment.url + "api/categories", addCat);
  }
}
