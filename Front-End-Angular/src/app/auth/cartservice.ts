import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from "../models/product";

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private apiUrl = 'http://localhost:8080/api/cart';

  constructor(private http: HttpClient) {}

  // Phương thức để lấy sản phẩm trong giỏ hàng
  getCartItems(userId: number): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl}/${userId}`);
  }

  // Phương thức để thêm sản phẩm vào giỏ hàng
  addToCart(product: Product, userId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/${userId}/add`, product);
  }
}
