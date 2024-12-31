import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {QuestionDetail} from '../../interfaces/questionDetail';
import {QuestionShort} from "../../interfaces/questionShort";

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  URL: string = "https://quizmaster-backend-emku.onrender.com/api/questions";

  constructor(private httpClient: HttpClient) {
  }

  public getAllQuestions(): Observable<QuestionShort[]> {
    return this.httpClient.get<QuestionShort[]>(`${this.URL}`);
  }

  public getAllFavoriteQuestions(): Observable<QuestionShort[]> {
    return this.httpClient.get<QuestionShort[]>(`${this.URL}/favorites`);
  }

  public getQuestionById(id: number): Observable<QuestionDetail> {
    return this.httpClient.get<QuestionDetail>((`${this.URL}/${id}`));
  }

  public addQuestion(question: QuestionDetail): Observable<QuestionDetail> {
    return this.httpClient.post<QuestionDetail>(`${this.URL}`, question);
  }

  public updateQuestion(question: QuestionDetail): Observable<QuestionDetail> {
    return this.httpClient.put<QuestionDetail>(`${this.URL}`, question);
  }

  public deleteQuestion(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.URL}/${id}`);
  }
}
