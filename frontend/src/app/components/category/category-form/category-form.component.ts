import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CategoryService} from "../../../services/category/category.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CategoryDetail} from "../../../interfaces/categoryDetail";
import {removeNullValues} from "../../../helpers/form-helpers";

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.css']
})
export class CategoryFormComponent {
  form: FormGroup;

  // id from URL, is 0 when no id found
  id: number = Number(this.activatedRoute.snapshot.paramMap.get('id'));
  hasNoQuizzes: boolean = true;

  colorsList: { id: number, hex: string }[] = [
    {id: 1, hex: '#2E2E3A'},
    {id: 2, hex: '#4C4C4C'},
    {id: 3, hex: '#2C3E50'},
    {id: 4, hex: '#008080'},
    {id: 5, hex: '#00008B'},
    {id: 6, hex: '#013220'},
    {id: 7, hex: '#006400'},
    {id: 8, hex: '#7A1C1C'},
    {id: 9, hex: '#A34700'},
    {id: 10, hex: '#8E0F5E'},
    {id: 11, hex: '#5C005C'},
    {id: 12, hex: '#5C4A06'}
  ];


  constructor(
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private categoryService: CategoryService
  ) {
    this.form = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      password: [null, [Validators.required, Validators.minLength(6)]],
      color: [this.colorsList[0].hex, Validators.required],
    });

    this.loadExistingCategory();
  }

  private loadExistingCategory() {
    if (this.id > 0) {
      this.categoryService.getCategoryById(this.id).subscribe(data => {

        if (data) {
          let dataForForm: CategoryDetail = data;
          this.hasNoQuizzes = data.quizzes?.length == 0;
          // Patch data that needs no logic
          this.form.patchValue({
            name: dataForForm.name,
            description: dataForForm.description,
          });

          if (dataForForm.color) {
            const selectedColor = this.colorsList.find(color => color.hex === data.color);
            if (selectedColor) {
              this.form.patchValue({
                color: selectedColor.hex,
              });
            }
          }

          // Remove validation from password -> filed hidden on edit
          this.form.get('password')?.removeValidators(Validators.required);
          this.form.get('password')?.updateValueAndValidity();
        }
      });
    }
  }


  onSubmit(): void {
    if (this.form.valid) {
      let formData = this.form.value;

      // Remove null or undefined values from form data
      let cleanedForm = removeNullValues(formData);

      if (this.id > 0) {
        cleanedForm.id = this.id;
        this.categoryService.updateCategory(cleanedForm).subscribe({
          next: () => this.router.navigateByUrl('/categories'),
          error: err => console.error('Loading category failure:', err)
        });
      } else {
        this.categoryService.addCategory(cleanedForm).subscribe({
          next: () => this.router.navigateByUrl('/categories'),
          error: err => console.error('Creating category failure:', err)
        });
      }

    }
  }

  onCancel(): void {
    this.router.navigateByUrl('/categories');
  }

  onDelete(): void {
    if (this.id === 0) return;

    const confirmDelete = confirm('Are you sure you want to delete this category?');
    if (!confirmDelete) return;

    this.categoryService.deleteCategory(this.id).subscribe({
      next: () => {
        this.router.navigateByUrl('/categories');
      },
      error: err => console.error('Loading category failed:', err)
    });
  }
}
