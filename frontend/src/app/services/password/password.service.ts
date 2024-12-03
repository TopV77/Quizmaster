import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {AuthResponse} from "../../interfaces/AuthResponse";

@Injectable({
  providedIn: 'root'
})
export class PasswordService {
  API_URL: string = "http://localhost:8080/api";

  constructor(private httpClient: HttpClient) { }

  public verifyCategoryPassword(id: number, pw: object): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>(`${this.API_URL}/categories/${id}/check-password`, pw);
  }

  public verifyCategoryToken(id: number, token: object): Observable<boolean> {
    return this.httpClient.post<boolean>(`${this.API_URL}/categories/${id}/check-token`, token);
  }

  public verifyQuizPassword(id: number, pw: object): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>(`${this.API_URL}/quizzes/${id}/check-password`, pw);
  }

  public verifyQuizToken(id: number, token: object): Observable<boolean> {
    return this.httpClient.post<boolean>(`${this.API_URL}/quizzes/${id}/check-token`, token);
  }

  public verifyQuestionPassword(id: number, pw: object): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>(`${this.API_URL}/questions/${id}/check-password`, pw);
  }

  public verifyQuestionToken(id: number, token: object): Observable<boolean> {
    return this.httpClient.post<boolean>(`${this.API_URL}/questions/${id}/check-token`, token);
  }
}
