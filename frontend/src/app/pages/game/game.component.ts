import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {QuestionDetail} from "../../interfaces/questionDetail";
import {GameService} from "../../services/game/game.service";
import {AnswerInfo} from "../../interfaces/answerInfo";
import {GameInfo} from "../../interfaces/gameInfo";

@Component({
    selector: 'app-game',
    templateUrl: './game.component.html',
    styleUrl: './game.component.css'
})
export class GameComponent {

    currentQuestions: QuestionDetail[] = [];
    gameInfo?: GameInfo;
    questionPointer: number = 0;
    answersGiven: AnswerInfo[] = [];

    constructor(private gameService: GameService, private router: Router) {
        this.gameService.question$.subscribe(data => {
            if (data.length === 0) {
                this.router.navigate(['/']);
            }
            this.currentQuestions = data;
        })

      this.gameService.gameInfo$.subscribe(data => {
        this.gameInfo = data;
      })
    }

    nextQuestion() {
        if (this.questionPointer === this.currentQuestions.length) {
            return
        }
        this.questionPointer += 1;
    }

    onAnswerSubmitted($event: AnswerInfo) {
        this.answersGiven.push($event);
        this.nextQuestion();
    }
}




