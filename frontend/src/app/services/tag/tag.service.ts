import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TagDetail} from '../../interfaces/tagDetail';
import {TagShort} from "../../interfaces/tagShort";

@Injectable({
  providedIn: 'root'
})
export class TagService {
  URL: string = "http://localhost:8080/api/tags";

  constructor(private httpClient: HttpClient) {
  }

  public getAllTags(): Observable<TagShort[]> {
    return this.httpClient.get<TagShort[]>(`${this.URL}`);
  }

  public getTagById(id: number): Observable<TagDetail> {
    return this.httpClient.get<TagDetail>((`${this.URL}/${id}`));
  }

  public addTag(tag: TagShort): Observable<TagShort> {
    return this.httpClient.post<TagShort>(`${this.URL}`, tag);
  }

  public updateTag(tag: TagShort): Observable<TagShort> {
    return this.httpClient.put<TagShort>(`${this.URL}`, tag);
  }

  public deleteTag(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.URL}/${id}`);
  }

}
