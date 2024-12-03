import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {TagShort} from "../../../interfaces/tagShort";
import {QuizService} from "../../../services/quiz/quiz.service";
import {ActivatedRoute, Router} from "@angular/router";
import {removeNullValues} from "../../../helpers/form-helpers";
import {CategoryService} from "../../../services/category/category.service";
import {QuizDetail} from "../../../interfaces/quizDetail";

@Component({
  selector: 'app-quiz-form',
  templateUrl: './quiz-form.component.html',
  styleUrl: './quiz-form.component.css'
})

export class QuizFormComponent {

  form: FormGroup;

  // id from URL, is 0 when no id found
  id: number = Number(this.activatedRoute.snapshot.paramMap.get('id'));

  // All existing categories
  categoryList: TagShort[] = [];
  quizHasQuestions: boolean = false; //Only used for Deletebutton


  constructor(
    private fb: FormBuilder,
    private categoryService: CategoryService,
    private quizService: QuizService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    this.form = this.fb.group({
      id: [],
      name: [null, [Validators.required]],
      description: [null, [Validators.required]],
      password: [null, [Validators.required, Validators.minLength(6)]],
      isFavorite: [false],
      category: [null, [Validators.required]],
    });

    this.loadOptions();
    this.loadExistingQuiz();
  }


  private loadOptions() {
    this.categoryService.getAllCategories().subscribe(data => {
      this.categoryList = data;
    });
  }

  private loadExistingQuiz() {
    if (this.id > 0) {
      this.quizService.getQuizById(this.id).subscribe(data => {

        if (data) {
          let dataForForm: QuizDetail = data;
          this.quizHasQuestions = data.questions.length === 0;

          // Patch data that needs no logic
          this.form.patchValue({
            name: dataForForm.name,
            description: dataForForm.description,
            isFavorite: dataForForm.isFavorite,
            category: [dataForForm.category]
          });

          // Remove validation from password -> filed hidden on edit
          this.form.get('password')?.removeValidators(Validators.required);
          this.form.get('password')?.updateValueAndValidity();

        }
      });
    }
  }


  onSubmit() {

    if (this.form.valid) {
      let formData = this.form.value;

      // Change category to an obj instead of object[]
      formData.category = formData.category[0];

      // Remove null or undefined values from form data
      let cleanedForm = removeNullValues(formData);

      console.log(cleanedForm);

      // Do the PUT / POST requests
      if (this.id > 0) {
        cleanedForm.id = this.id;
        this.quizService.updateQuiz(cleanedForm).subscribe({
          next: (question) => this.router.navigate(['/quiz', question.id]),
          error: err => console.error('Update quiz failed:', err)
        });
      } else {
        this.quizService.addQuiz(cleanedForm).subscribe({
          next: () => this.router.navigateByUrl('/quizzes'),
          error: err => console.error('Create quiz failed:', err)
        });
      }

    }
  }
}
