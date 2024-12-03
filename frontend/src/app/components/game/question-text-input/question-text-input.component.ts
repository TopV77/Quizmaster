import {Component, EventEmitter, Input, Output} from '@angular/core';
import { QuestionDetail } from '../../../interfaces/questionDetail';
import {AnswerInfo} from "../../../interfaces/answerInfo";

@Component({
  selector: 'app-question-text-input',
  templateUrl: './question-text-input.component.html',
  styleUrls: ['./question-text-input.component.css']
})
export class QuestionTextInputComponent {
  @Input() currentQuestion!: QuestionDetail;
  @Output() submitSelectedAnswer = new EventEmitter<AnswerInfo>();
  answerSubmitted: boolean = false;
  userAnswer: string = '';
  userInfoIsCorrect: boolean = false;

  submitAnswer(): void {
    const answer: AnswerInfo = {
      question: this.currentQuestion.question,
      answerGiven: this.userAnswer,
      correctAnswer: this.currentQuestion.correctAnswer,
      isCorrect: this.userInfoIsCorrect,
    };

    this.submitSelectedAnswer.emit(answer);
    this.answerSubmitted = false;
    this.userAnswer = '';
  }

  markAsCorrect(): void {
    this.userInfoIsCorrect = true;
    this.submitAnswer();
  }

  markAsIncorrect(): void {
    this.userInfoIsCorrect = false;
    this.submitAnswer();
  }

  submitInfo() {
    this.answerSubmitted = true;
  }
}
