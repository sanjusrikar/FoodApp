import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Food } from '../model/food';
import { Menu } from '../model/menu';
import { Data } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {

  constructor(private httpClient : HttpClient) { }

  private baseURL = "http://localhost:9191/";



  createMenu(menu:Menu,id:number){
    return this.httpClient.post<any>(`${this.baseURL}` + 'createMenu/' + id, menu) 
  }

   addItemsToMenu(foods:Food[],id:number){
    return this.httpClient.post(`${this.baseURL}` + 'addItemsToMenu/' + id, foods,{responseType: 'text'})
  }

}
