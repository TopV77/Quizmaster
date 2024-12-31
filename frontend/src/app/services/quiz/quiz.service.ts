import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { QuizDetail } from '../../interfaces/quizDetail';
import { QuizShort } from '../../interfaces/quizShort';

@Injectable({
  providedIn: 'root'
})
export class QuizService {
  URL: string = "https://quizmaster-backend-emku.onrender.com/api/quizzes";

  constructor(private httpClient: HttpClient) {
  }

  public getAllQuizzes(): Observable<QuizShort[]> {
    return this.httpClient.get<QuizShort[]>(`${this.URL}`);
  }

  public getQuizById(id: number): Observable<QuizDetail> {
    return this.httpClient.get<QuizDetail>((`${this.URL}/${id}`));
  }

  public addQuiz(quiz: QuizDetail): Observable<QuizDetail> {
    return this.httpClient.post<QuizDetail>(`${this.URL}`, quiz);
  }

  public updateQuiz(quiz: QuizDetail): Observable<QuizDetail> {
    return this.httpClient.put<QuizDetail>(`${this.URL}`, quiz);
  }

  public deleteQuiz(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.URL}/${id}`);
  }

}
