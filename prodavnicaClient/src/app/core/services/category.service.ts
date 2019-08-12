import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Category } from '../models/category.model';
import { AddUpdateCategory } from '../models/addUpdateCategory.model';
import { CategoryInfo } from '../models/categoryInfo.model';

@Injectable({
  providedIn: "root"
})
export class CategoryService {
  constructor(private http: HttpClient, private httpParams: HttpParams) { }

  public getAll(): Observable<any> {
    return this.http.get(environment.url + "api/categories");
  }

  public getAllBooksFromCategories(category: Category): Observable<any> {
    // ...console.log for DEBBUGING purposes
    // console.log('(inside of category.service.ts) category:', category);
    // console.log('(inside of category.service.ts) category.categoryId:', category.categoryId);
    // console.log('(inside of category.service.ts) this.httpParams:', this.httpParams);
    // console.log('(inside of category.service.ts) this.httpParams.toString():', this.httpParams.toString());    
    // console.log('(inside of category.service.ts) this.httpParams.keys():', this.httpParams.keys());
    
    return this.http.get(environment.url + "api/categories/getAllBooksFromCategories", { 
      params: this.httpParams.set('id', category.categoryId.toString())
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
