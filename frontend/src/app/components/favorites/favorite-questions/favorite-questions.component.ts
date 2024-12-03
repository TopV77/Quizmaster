import {Component} from '@angular/core';
import {QuestionShort} from "../../../interfaces/questionShort";
import {QuestionService} from "../../../services/question/question.service";
import {Router} from "@angular/router";
import {GameService} from "../../../services/game/game.service";

@Component({
  selector: 'app-favorite-questions',
  templateUrl: './favorite-questions.component.html',
  styleUrl: './favorite-questions.component.css'
})
export class FavoriteQuestionsComponent {

  favorites: QuestionShort[] = [];

  constructor(
    private questionService: QuestionService,
    private router: Router,
    private gameService: GameService
  ) {
    this.questionService.getAllFavoriteQuestions().subscribe(data => {
      this.favorites = data;
    })
  }

  startQuiz(favorites: QuestionShort[]) {
    this.gameService.shortToDetailedQuestion(favorites).subscribe(detailedQuestions => {
      this.gameService.addAllQuestions(detailedQuestions);
      this.router.navigateByUrl('/game');
    });
  }
}
