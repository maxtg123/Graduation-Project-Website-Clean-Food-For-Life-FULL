import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Product} from "../models/product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {



  private apiUrl = 'http://localhost:8080/api/products';

  constructor(private http: HttpClient) {}

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl}/list`); // Giả sử bạn có endpoint '/list' để lấy sản phẩm
  }





}
