import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {QuestionDetail} from "../../../interfaces/questionDetail";
import {AnswerInfo} from "../../../interfaces/answerInfo";

@Component({
    selector: 'app-question-single-choice',
    templateUrl: './question-single-choice.component.html',
    styleUrls: ['./question-single-choice.component.css']
})
export class QuestionSingleChoiceComponent {
    @Input() currentQuestion!: QuestionDetail;
    @Output() submitSelectedAnswer: EventEmitter<AnswerInfo> = new EventEmitter<AnswerInfo>();

    form: FormGroup;

    constructor(private formBuilder: FormBuilder) {
        this.form = formBuilder.group({
            answerGiven: ['']
        });
    }

    submit(): void {

        let answerGivenText: string = '';
        switch (this.form.value['answerGiven']) {
            case 'A':
                answerGivenText = this.currentQuestion.answerA!;
                break;
            case 'B':
                answerGivenText = this.currentQuestion.answerB!;
                break;
            case 'C':
                answerGivenText = this.currentQuestion.answerC!;
                break;
            case 'D':
                answerGivenText = this.currentQuestion.answerD!;
                break;
        }

        let correctAnswerText: string = '';
        switch (this.currentQuestion.correctAnswer) {
            case 'A':
                correctAnswerText = this.currentQuestion.answerA!;
                break;
            case 'B':
                correctAnswerText = this.currentQuestion.answerB!;
                break;
            case 'C':
                correctAnswerText = this.currentQuestion.answerC!;
                break;
            case 'D':
                correctAnswerText = this.currentQuestion.answerD!;
                break;
        }

        let answerInfo: AnswerInfo = {
            question: this.currentQuestion.question,
            answerGiven: answerGivenText,
            correctAnswer: correctAnswerText,
            isCorrect: answerGivenText === correctAnswerText
        }

        this.form.reset();
        this.submitSelectedAnswer.emit(answerInfo);
    }
}
