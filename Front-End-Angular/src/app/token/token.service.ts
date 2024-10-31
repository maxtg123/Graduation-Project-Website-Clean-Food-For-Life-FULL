import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  private tokenKey = 'authToken'; // Khóa lưu trữ token trong localStorage

  // Kiểm tra xem người dùng đã đăng nhập chưa
  isAuthenticated(): boolean {
    return this.getToken() !== null && !this.isTokenExpired(); // Trả về true nếu có token và chưa hết hạn
  }

  // Lưu token vào localStorage
  saveToken(token: string): void {
    localStorage.setItem(this.tokenKey, token); // Lưu token
  }

  // Lấy token từ localStorage
  getToken(): string | null {
    return localStorage.getItem(this.tokenKey); // Trả về token nếu có
  }

  // Xóa token khi đăng xuất
  clearAuth(): void {
    localStorage.removeItem(this.tokenKey); // Xóa token
  }

  // Phương thức đăng xuất
  logout(): void {
    this.clearAuth(); // Gọi clearAuth để xóa token
  }

  // Phương thức kiểm tra thời gian hết hạn của token (nếu có)
  isTokenExpired(): boolean {
    const token = this.getToken();
    if (!token) return true; // Nếu không có token, coi như đã hết hạn

    const payload = JSON.parse(atob(token.split('.')[1])); // Giải mã payload
    const currentTime = Math.floor(Date.now() / 1000); // Thời gian hiện tại tính bằng giây
    return payload.exp < currentTime; // Kiểm tra xem token đã hết hạn chưa
  }
}
