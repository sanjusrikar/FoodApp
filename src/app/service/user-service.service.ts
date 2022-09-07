import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Food } from '../model/food';
import { FoodOrder } from '../model/food-order';
import { User } from '../model/user';
import { Router } from '@angular/router';
import { OrderObject } from '../model/order-object';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  private baseURL = "http://localhost:9191/";
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private httpClient : HttpClient,
    private router : Router) { }

  login(user: User){
    return this.httpClient.post<any>(`${this.baseURL}` + 'saveUser',user)
  }
  logout(){
    localStorage.removeItem('currentUserId');
    localStorage.removeItem('currentUserRole');
    localStorage.clear();
    this.router.navigateByUrl('/login')
  }

  placeOrder(order : OrderObject,id:number){
    return this.httpClient.post<any>(`${this.baseURL}` + "placeOrder/" + id, order);
  }

  createCart(foods : Food[] , order_id:number){
    return this.httpClient.post<FoodOrder>(`${this.baseURL}` + "addItemsToOrder/" + order_id, foods);

  }

  sendMail(id:number){
    return this.httpClient.post(`${this.baseURL}` + "sendMail/" + id,null);
  }

  readyOrder(id:number){
    return this.httpClient.post(`${this.baseURL}` + "readyOrder/" + id,null);
  }



}
