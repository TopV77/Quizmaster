import {Component, Input} from '@angular/core';
import {QuizShort} from "../../../interfaces/quizShort";
import {QuizService} from "../../../services/quiz/quiz.service";

@Component({
  selector: 'app-quiz-card-grid',
  templateUrl: './quiz-card-grid.component.html',
  styleUrl: './quiz-card-grid.component.css'
})
export class QuizCardGridComponent {

  @Input() mode: "default" | "latest" | "suggestions" | "fav" = 'default';

  quizzes: QuizShort[] = []

  constructor(private quizService: QuizService) {
    this.quizService.getAllQuizzes().subscribe(data => {

      switch (this.mode) {
        case 'latest':
          this.quizzes = this.getLastElements(data, 3);
          break; // Get the last 3 elements
        case 'suggestions':
          this.quizzes = this.getRandomQuizzes(data, 3);
          break;
        case 'fav':
          this.quizzes = data.filter((quiz: QuizShort) => quiz.isFavorite);
          break;
        default:
          this.quizzes = data;
          break
      }

    })
  }

  private getLastElements(quizzes: QuizShort[], count: number) {
    return quizzes.slice(-count);
  }

  private getRandomQuizzes(quizzes: QuizShort[], count: number): QuizShort[] {
    // Create a copy of the array to avoid modifying the original
    const shuffled = quizzes.slice();

    // Fisher-Yates Shuffle Algorithm
    for (let i = shuffled.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1)); // Random index between 0 and i
      [shuffled[i], shuffled[j]] = [shuffled[j], shuffled[i]]; // Swap elements
    }

    // Return the first to 'count' elements
    return shuffled.slice(0, count);
  }
}
