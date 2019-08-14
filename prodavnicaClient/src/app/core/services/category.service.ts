import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Category } from '../models/category.model';
import { AddUpdateCategory } from '../models/addUpdateCategory.model';
import { CategoryInfo } from '../models/categoryInfo.model';
import { ThrowStmt } from '@angular/compiler';

@Injectable({
  providedIn: "root"
})
export class CategoryService {
  constructor(private http: HttpClient, private httpParams: HttpParams) {
  }

  public getAll(): Observable<any> {
    return this.http.get(environment.url + "api/categories");
  }

  public getAllBooksFromCategories(categories: number[]): Observable<any> {
    let categoryIdsSet = new Set<number>();
    categories.forEach(x => categoryIdsSet.add(x));
    
    let categoriesStr: string[] = [];
    categoryIdsSet.forEach( x => categoriesStr.push(x.toString()));
    console.log('categoriesStr: ', categoriesStr);

    this.httpParams =  new HttpParams({
      fromObject: { id : categoriesStr}
    });
    categoryIdsSet.clear();
    categoriesStr = [];
  
    return this.http.get(environment.url + "api/categories/getAllBooksFromCategories", {
      params: this.httpParams
    });
  }

  updateCategory(updateCat: AddUpdateCategory, id: number): Observable<any> {
    return this.http.put(environment.url + "api/categories/" + id, updateCat);
  }

  deleteCategory(id: number): Observable<any> {
    return this.http.delete(environment.url + "/api/categories/" + id);
  }
  addCategory(addCat: AddUpdateCategory): Observable<any> {
    return this.http.post(environment.url + "/api/categories", addCat);
  }
}
