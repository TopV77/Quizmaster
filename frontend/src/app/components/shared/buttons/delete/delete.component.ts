import {Component, Input} from '@angular/core';
import {QuizService} from "../../../../services/quiz/quiz.service";
import {CategoryService} from "../../../../services/category/category.service";
import {QuestionService} from "../../../../services/question/question.service";
import {TagService} from "../../../../services/tag/tag.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrl: './delete.component.css'
})
export class DeleteComponent {
  @Input() itemId: number = 0;
  @Input() mode: "category" | "quiz" | "tag" | "question" = "tag";

  constructor(private quizService: QuizService,
              private categoryService: CategoryService,
              private questionService: QuestionService,
              private tagService: TagService,
              private router: Router) {
  }

  delete() {
    switch (this.mode) {
      case "category":
        this.categoryService.deleteCategory(this.itemId).subscribe(() => this.router.navigateByUrl('/categories'));
        break;
      case "quiz":
        this.quizService.deleteQuiz(this.itemId).subscribe(() => this.router.navigateByUrl('/quizzes'));
        break;
      case "question":
        this.questionService.deleteQuestion(this.itemId).subscribe(() => this.router.navigateByUrl('/questions'));
        break;
      default:
        this.tagService.deleteTag(this.itemId).subscribe(() => this.router.navigateByUrl('/tags'));
        break;
    }
  }
}
