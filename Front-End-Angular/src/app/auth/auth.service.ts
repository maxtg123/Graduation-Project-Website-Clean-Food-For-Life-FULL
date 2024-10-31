import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators'; // Import các operator cần thiết
import { AuthenticationRequest } from "../models/authenticationrequest";
import { RegistrationRequest } from "../models/registration-request";
import { AuthenticationResponse } from "../models/authentication-response";
import { TokenService } from "../token/token.service";

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient, private tokenService: TokenService) {}

  // Phương thức xác thực người dùng
  authenticate(authRequest: AuthenticationRequest): Observable<AuthenticationResponse> {
    return this.http.post<AuthenticationResponse>(`${this.baseUrl}/login`, authRequest).pipe(
      tap(response => {
        if (response.token) { // Kiểm tra xem token có tồn tại không
          this.tokenService.saveToken(response.token); // Lưu token nếu có
        }
      }),
      catchError(err => {
        // Xử lý lỗi từ server và trả về thông báo lỗi
        return throwError(err.error.message || 'Login failed'); // Trả về lỗi
      })
    );
  }

  // Kích hoạt tài khoản
  activateAccount(data: { code: string }): Observable<string> {
    return this.http.post(`${this.baseUrl}/activate-account`, data, { responseType: 'text' }).pipe(
      catchError(err => {
        return throwError(err.error.message || 'Activation failed'); // Trả về lỗi
      })
    );
  }

  // Kiểm tra xem người dùng đã đăng nhập hay chưa
  isUserLoggedIn(): boolean {
    return this.tokenService.isAuthenticated();
  }

  // Phương thức đăng ký người dùng
  register(request: RegistrationRequest): Observable<string> {
    return this.http.post(`${this.baseUrl}/register`, request, { responseType: 'text' }).pipe(
      catchError(err => {
        return throwError(err.error.message || 'Registration failed'); // Trả về lỗi
      })
    );
  }

  // Phương thức đăng nhập chính xác với AuthenticationRequest
  login(authRequest: AuthenticationRequest): Observable<AuthenticationResponse> {
    return this.authenticate(authRequest); // Sử dụng authenticate
  }
}
