import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {QuizShort} from "../../../interfaces/quizShort";
import {GameService} from "../../../services/game/game.service";
import {Router} from "@angular/router";
import {CategoryService} from "../../../services/category/category.service";

@Component({
  selector: 'app-quiz-card',
  templateUrl: './quiz-card.component.html',
  styleUrl: './quiz-card.component.css'
})
export class QuizCardComponent implements OnChanges {

  @Input() quiz?: QuizShort;
  quizCategoryColor: string = '';

  constructor(private gameService: GameService, private router: Router, private categoryService: CategoryService) {
  }

  startQuiz(quiz: QuizShort) {

    if (quiz.questionSize === 0) {
      alert("You cannot start the quiz without questions. Please add questions to the quiz first.");
      return;
    }
    this.gameService.shortQuizToDetailedQuestions(quiz).subscribe(detailedQuestions => {
      this.gameService.addAllQuestions(detailedQuestions);
      this.router.navigateByUrl('/game');
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes['quiz'].currentValue !== undefined) {
      this.categoryService.getCategoryById(this.quiz!.category.id!).subscribe( category => {
        this.quizCategoryColor = category.color || '#FFFFFF';
      })
    }
  }
}
