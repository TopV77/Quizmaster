import {Injectable} from '@angular/core';
import {BehaviorSubject, forkJoin, Observable, switchMap} from "rxjs";
import {QuestionDetail} from "../../interfaces/questionDetail";
import {QuestionShort} from "../../interfaces/questionShort";
import {QuestionService} from "../question/question.service";
import {QuizShort} from "../../interfaces/quizShort";
import {QuizService} from "../quiz/quiz.service";
import {GameInfo} from "../../interfaces/gameInfo";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private _questionSubject = new BehaviorSubject<QuestionDetail[]>([]);
  question$ = this._questionSubject.asObservable();

  private _gameInfoSubject = new BehaviorSubject<GameInfo>({
    name: "Favorite Questions",
    category: "Mixed categories",
    description: "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt"
  });
  gameInfo$ = this._gameInfoSubject.asObservable();


  get questionSubject(): BehaviorSubject<QuestionDetail[]> {
    return this._questionSubject;
  }

  constructor(private questionService: QuestionService, private quizService: QuizService) {
  }


  addAllQuestions(questions: QuestionDetail[]) {
    this._questionSubject.next(questions);
  }

  addGameInfo(name: string, category: string, description: string) {
    const gameInfo = {
      name: name,
      category: category,
      description: description
    }
    this._gameInfoSubject.next(gameInfo);
  }

  addQuestions(question: QuestionDetail) {
    let questionsUpdate = this._questionSubject.value;
    questionsUpdate.push(question);
    this._questionSubject.next(questionsUpdate);
  }

  resetQuestion() {
    this._questionSubject.next([]);
  }

  shortToDetailedQuestion(questions: QuestionShort[]): Observable<QuestionDetail[]> {
    const observables: Observable<QuestionDetail>[] = questions
      .filter(question => question.id !== undefined)  // Filter out undefined IDs
      .map(question => this.questionService.getQuestionById(question.id as number));

    return forkJoin(observables);
  }

  shortQuizToDetailedQuestions(quiz: QuizShort): Observable<QuestionDetail[]> {
    return this.quizService.getQuizById(quiz.id!).pipe(
      switchMap((quizDetail) => {
        const questions = quizDetail.questions ?? [];
        return this.shortToDetailedQuestion(questions);
      })
    );
  }
}
