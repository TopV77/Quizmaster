import {Component, Input} from '@angular/core';
import {QuestionShort} from "../../../interfaces/questionShort";

@Component({
  selector: 'app-questions-table',
  templateUrl: './questions-table.component.html',
  styleUrl: './questions-table.component.css'
})
export class QuestionsTableComponent {
  @Input() questions:QuestionShort[] = [];
  @Input() mode: "fav" | "default" = 'default';
}
