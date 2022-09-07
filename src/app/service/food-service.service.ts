import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Food } from '../model/food';

@Injectable({
  providedIn: 'root'
})
export class FoodServiceService {

  private baseURL = "http://localhost:9191/";
  
  constructor(private httpClient : HttpClient) { }
  getAllFoods() : Observable<Food[]>{
    return this.httpClient.get<Food[]>(`${this.baseURL}` + "getAllFoods");
  }
}
