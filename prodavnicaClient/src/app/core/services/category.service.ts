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

  public getAllBooksFromCategories(categories: Category[]): Observable<any> { 
    const hParams: HttpParams = new HttpParams();
    for(let category of categories){
      console.log("(in-for) category: ", category);      
      this.httpParams.set('id', category.categoryId.toString());
    }
    console.log("this.httpParams.toString(): ", this.httpParams.get('id'));
    
    // HttpParams hParams = Object.getOwnPropertyNames(category).reduce((p,key) => v.set(key,category[key]), new HttpParams());
    return this.http.get(environment.url + "api/categories/getAllBooksFromCategories", { 
      params:  this.httpParams
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
