import {Component} from '@angular/core';
import {QuestionDetail} from "../../interfaces/questionDetail";
import {QuestionService} from "../../services/question/question.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-question-details',
  templateUrl: './question-details.component.html',
  styleUrl: './question-details.component.css'
})
export class QuestionDetailsComponent {
  questionDetail?: QuestionDetail;

  constructor(private questionService: QuestionService, private activatedRoute: ActivatedRoute, private router: Router) {
    const id = this.activatedRoute.snapshot.paramMap.get('id');
    this.questionService.getQuestionById(parseInt(id!)).subscribe(data => this.questionDetail = data);
  }
}
