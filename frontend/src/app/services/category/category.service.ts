import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { CategoryDetail } from '../../interfaces/categoryDetail';
import { CategoryShort } from '../../interfaces/categoryShort';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  URL: string = "http://localhost:8080/api/categories";

  constructor(private httpClient: HttpClient) {
  }

  public getAllCategories(): Observable<CategoryShort[]> {
    return this.httpClient.get<CategoryShort[]>(`${this.URL}`);
  }

  public getCategoryById(id: number): Observable<CategoryDetail> {
    return this.httpClient.get<CategoryDetail>((`${this.URL}/${id}`));
  }

  public addCategory(category: CategoryDetail): Observable<CategoryDetail> {
    return this.httpClient.post<CategoryDetail>(`${this.URL}`, category);
  }

  public updateCategory(category: CategoryDetail): Observable<CategoryDetail> {
    return this.httpClient.put<CategoryDetail>(`${this.URL}`, category);
  }

  public deleteCategory(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.URL}/${id}`);
  }

}

