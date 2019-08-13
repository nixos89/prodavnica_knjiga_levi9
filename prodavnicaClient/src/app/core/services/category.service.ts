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
  constructor(private http: HttpClient, private httpParams: HttpParams, private catServiceSet: Set<string>) { 
    // this.catServiceSet = new Set<string>(); 
    // this.httpParams = new HttpParams();
  }

  public getAll(): Observable<any> {
    return this.http.get(environment.url + "api/categories");
  }


  // getAllBooksFromCategories::START
  public getAllBooksFromCategories(categories: number[]): Observable<any> {

    let categoryIdsString: string = "";
    // categories.forEach( x => categoryIdsString.concat('id=' + x.toString() + "&")  );
    for(let category of categories){
      // var str = new String('id=' + category.toString() + "&").toString();
      console.log('id=' + category.toString() + "&");
      
      categoryIdsString+='id=' + category.toString() + "&";
      console.log('(in da for-loop) categoryIdsString: ', categoryIdsString);  
    }

    console.log('categoryIdsString: ', categoryIdsString);
    
    var hParams: HttpParams = new HttpParams( {fromString: categoryIdsString } );
    console.log('this.httpParams.keys(): ', this.httpParams.keys());
    
    // HttpParams hParams = Object.getOwnPropertyNames(category).reduce((p,key) => v.set(key,category[key]), new HttpParams());
    return this.http.get(environment.url + "api/categories/getAllBooksFromCategories", {
      params: hParams
    });
  }
  // getAllBooksFromCategories::END


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
