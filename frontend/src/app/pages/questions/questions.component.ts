import {Component} from '@angular/core';
import {QuestionShort} from '../../interfaces/questionShort';
import {QuestionService} from '../../services/question/question.service';
import {Router} from "@angular/router";


@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrl: './questions.component.css'
})
export class QuestionsComponent{
  questions: QuestionShort[] = [];
  filteredQuestions: QuestionShort[] = [];
  selectedFilter: string = 'All';

  constructor(private questionService: QuestionService, private router: Router) {
    this.questionService.getAllQuestions().subscribe(data => {
      this.questions = data;
      this.filterQuestions('All');
    })
  }

  filterQuestions(filter: string): void {
    this.selectedFilter = filter;

    if (filter === 'All') {
      this.filteredQuestions = this.questions;
    } else {
      this.filteredQuestions = this.questions.filter(q => q.questionType === filter);
    }
  }

  ngOnChanges(): void {
    this.filterQuestions(this.selectedFilter);
  }

}
