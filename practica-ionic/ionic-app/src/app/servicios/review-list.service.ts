import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Review } from '../model/Review';

@Injectable({
  providedIn: 'root'
})
export class ReviewListService {

  constructor(private httpClient: HttpClient) { }

  findAll(): Observable<Review[]> {
    let params = new HttpParams();
    params = params.append('page', 0);
    params = params.append('size', 10);
    params = params.append('sortDir', 'desc');
    params = params.append('sort', 'id');
    return this.httpClient.get<Review[]>("http://localhost:8080/reviews", {params});
  }
}
