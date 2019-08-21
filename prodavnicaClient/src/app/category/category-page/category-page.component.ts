import { Component, OnInit } from '@angular/core';
import {CategoryService} from '../../core/services/category.service';
import {CategoryInfo} from '../../core/models/categoryInfo.model';
import {ToastrService} from 'ngx-toastr';
import {Category} from '../../core/models/category.model';
import {AddUpdateCategory} from '../../core/models/addUpdateCategory.model';
import {error} from 'util';
import { AuthenticationService } from 'src/app/core/services/authentication.service';

@Component({
  selector: 'app-category-page',
  templateUrl: './category-page.component.html',
  styleUrls: ['./category-page.component.css']
})
export class CategoryPageComponent implements OnInit {

  categoryData: CategoryInfo = new CategoryInfo();
  category: Category = new Category()
  upCat: AddUpdateCategory = new AddUpdateCategory();
  addCat: AddUpdateCategory= new AddUpdateCategory();

  constructor(private categoryService: CategoryService, public authService: AuthenticationService ,private toastr: ToastrService) { }

  ngOnInit() {
    this.getAllCategories();
  }
  getAllCategories()  {
    this.categoryService.getAll().subscribe(
      data  =>{
      this.categoryData= data;
    },error => {
          this.toastr.error("Failed to get categories");
      })
  }

  editDialogOpen(cat: Category) {
    this.category.categoryId = cat.categoryId;
    this.category.name = cat.name;
  }

  updateCategory(category: Category) {
    this.upCat.name=category.name;
    this.upCat.isDeleted=false;
  this.categoryService.updateCategory(this.upCat,category.categoryId).subscribe(
    data =>{
      this.toastr.success("Success");
      location.reload();
    },error => {
      this.toastr.error("Error");
    }
  )
  }

  deleteCategory(categoryId: number,i:number) {
    this.categoryService.deleteCategory(categoryId).subscribe(
      data =>{
        this.toastr.success("Category is deleted succesfully");
        this.categoryData.categories.splice(i,1);
      },error => {
        this.toastr.error(error.error.message);
      }
    )
  }

  addCategory(addCat: AddUpdateCategory) {
    addCat.isDeleted = false;
    this.categoryService.addCategory(addCat).subscribe(
      data =>{
        location.reload();
      },error1 => {
        this.toastr.error("Category cant be added");
      }
    )

  }
}
