import { Component } from '@angular/core';
import {CategoryShort} from "../../../interfaces/categoryShort";
import {CategoryService} from "../../../services/category/category.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-category-card-grid',
  templateUrl: './category-card-grid.component.html',
  styleUrl: './category-card-grid.component.css'
})
export class CategoryCardGridComponent {
  categories: CategoryShort[] = []

  constructor(private categoryService: CategoryService, private router: Router) {
    this.loadCategories();
  }

  loadCategories(): void {
    this.categoryService.getAllCategories().subscribe(data => {
      this.categories = data;
    });
  }

}
