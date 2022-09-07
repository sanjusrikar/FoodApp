import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FoodOrder } from '../model/food-order';
import { Observable } from 'rxjs';
import { Food } from '../model/food';

@Injectable({
  providedIn: 'root'
})
export class OrdersServiceService {

  
  private baseURL = "http://localhost:9191/";
  
  constructor(private httpClient : HttpClient) { }

  getAllOrders() : Observable<FoodOrder[]>{
    return this.httpClient.get<FoodOrder[]>(`${this.baseURL}` + "getAllFoodOrders");
  }

  getItemsForOrder(id : number): Observable<Food[]>{
    return this.httpClient.get<Food[]>(`${this.baseURL}` + "getItemsInOrder/" +id);
  }
  deleteOrder(id:number){
    return this.httpClient.delete(`${this.baseURL}` + "deleteFoodOrder/"+id)
  }
}


