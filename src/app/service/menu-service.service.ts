import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Menu } from '../model/menu';

@Injectable({
  providedIn: 'root'
})
export class MenuServiceService {

  constructor(private httpClient : HttpClient) { }
   
  private baseURL = "http://localhost:9191/";
  getAllMenu() {
    return this.httpClient.get<Menu[]>(`${this.baseURL}` + "getAllMenus")
  }
  deleteMenu(id:number) {
    return this.httpClient.delete(`${this.baseURL}` + "deleteMenu/" + id)
  }
}
