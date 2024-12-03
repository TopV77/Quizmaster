import {Component} from '@angular/core';
import {CategoryDetail} from "../../interfaces/categoryDetail";
import {CategoryService} from "../../services/category/category.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrl: './category-details.component.css'
})
export class CategoryDetailsComponent {
  categoryDetail?: CategoryDetail;

  constructor(private categoryService: CategoryService, private activatedRoute: ActivatedRoute, private router: Router) {
    const id = activatedRoute.snapshot.paramMap.get('id');
    this.categoryService.getCategoryById(parseInt(id!)).subscribe(data => this.categoryDetail = data);
  }
}
