import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { TokenService } from "../token/token.service";

@Injectable({
  providedIn: 'root', // Cung cấp AuthGuard toàn cục
})
export class AuthGuard implements CanActivate {

  constructor(private tokenService: TokenService, private router: Router) {}

  // Phương thức canActivate kiểm tra xem người dùng có được phép truy cập không
  canActivate(): boolean {
    if (this.tokenService.isAuthenticated()) {
      return true; // Người dùng đã đăng nhập, cho phép truy cập
    } else {
      this.router.navigate(['/login']); // Nếu chưa đăng nhập, điều hướng về trang login
      return false;
    }
  }
}
