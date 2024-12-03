import {Component, Input} from '@angular/core';
import {QuestionService} from "../../../../services/question/question.service";
import {Router} from "@angular/router";
import {GameService} from "../../../../services/game/game.service";
import {QuizShort} from "../../../../interfaces/quizShort";

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrl: './start.component.css'
})
export class StartComponent {
  @Input() quiz?: QuizShort;

  constructor(
    private questionService: QuestionService,
    private router: Router,
    private gameService: GameService
  ) {
  }

  clickHandler(): void {
    if (this.quiz) {
      this.startQuiz(this.quiz);
    } else {
      this.startQuizFromFavorites();
    }
  }

  startQuizFromFavorites() {
    this.questionService.getAllFavoriteQuestions().subscribe(data => {
      this.gameService.shortToDetailedQuestion(data).subscribe(detailedQuestions => {
        this.gameService.addAllQuestions(detailedQuestions);
        this.router.navigateByUrl('/game');
      });
    })
  }

  startQuiz(quiz: QuizShort) {
    if (quiz.questionSize === 0) {
      alert("You cannot start the quiz without questions. Please add questions to the quiz first.");
      return;
    }
    this.gameService.shortQuizToDetailedQuestions(quiz).subscribe(detailedQuestions => {
      this.gameService.addAllQuestions(detailedQuestions);

      if (quiz.description) {
        this.gameService.addGameInfo(quiz.name, quiz.category.name, quiz.description);
      } else {
        this.gameService.addGameInfo(quiz.name, quiz.category.name, '');
      }

      this.router.navigateByUrl('/game');
    });
  }

}
