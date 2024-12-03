import {Component, EventEmitter, Input, Output} from '@angular/core';
import {QuestionDetail} from "../../../interfaces/questionDetail";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AnswerInfo} from "../../../interfaces/answerInfo";


@Component({
  selector: 'app-question-multiple-choice',
  templateUrl: './question-multiple-choice.component.html',
  styleUrl: './question-multiple-choice.component.css'
})
export class QuestionMultipleChoiceComponent {
  @Input() currentQuestion!: QuestionDetail;
  @Output() answerSelected = new EventEmitter<AnswerInfo>();

  answerForm!: FormGroup;

  constructor(private fb: FormBuilder) {
    this.answerForm = this.fb.group({
      answerA: [false],
      answerB: [false],
      answerC: [false],
      answerD: [false]
    });
  }

  setSelectedAnswer(): void {
    const selectedAnswers: string[] = [];
    const controls = this.answerForm.controls;

    // Mapping von Buchstaben zu den entsprechenden Werten
    const answerValues: { [key: string]: string } = {
      A: this.currentQuestion.answerA ?? '',
      B: this.currentQuestion.answerB ?? '',
      C: this.currentQuestion.answerC ?? '',
      D: this.currentQuestion.answerD ?? ''
    };

    // Sammle die ausgewählten Buchstaben
    if (controls['answerA'].value) selectedAnswers.push('A');
    if (controls['answerB'].value) selectedAnswers.push('B');
    if (controls['answerC'].value) selectedAnswers.push('C');
    if (controls['answerD'].value) selectedAnswers.push('D');

    // Auflösen der ausgewählten Antworten zu ihren tatsächlichen Werten
    const givenAnswer = selectedAnswers
      .map(letter => answerValues[letter]) // Buchstaben zu Werten auflösen
      .join('; ');

    // Auflösen der korrekten Antworten zu ihren tatsächlichen Werten
    const correctAnswers = this.currentQuestion.correctAnswer
      .split('; ') // Buchstaben (z. B. "A; B") in ein Array aufteilen
      .map(letter => answerValues[letter]) // Buchstaben zu Werten auflösen
      .join('; ');

    // Überprüfung der Korrektheit
    const isCorrect = givenAnswer === correctAnswers;

    // Erstellung des AnswerInfo-Objekts
    const answerInfo: AnswerInfo = {
      question: this.currentQuestion.question,
      answerGiven: givenAnswer,
      correctAnswer: correctAnswers,
      isCorrect: isCorrect
    };

    this.answerSelected.emit(answerInfo); // Emit mit den aufgelösten Werten
    this.answerForm.reset(); // Formular zurücksetzen
  }
}
