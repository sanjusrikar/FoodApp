import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CartServiceService {

  private baseURL = "http://localhost:9191/";
  constructor(private httpClient : HttpClient) { }
}
