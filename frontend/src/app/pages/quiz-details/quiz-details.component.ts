import {Component} from '@angular/core';
import {QuizDetail} from "../../interfaces/quizDetail";
import {QuizService} from "../../services/quiz/quiz.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-quiz-details',
  templateUrl: './quiz-details.component.html',
  styleUrl: './quiz-details.component.css'
})
export class QuizDetailsComponent {
  quizDetail?: QuizDetail;

  constructor(private quizService: QuizService, private activatedRoute: ActivatedRoute, private router: Router) {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    this.quizService.getQuizById(parseInt(id!)).subscribe(data => this.quizDetail = data);
  }
}
