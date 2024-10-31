import { Component, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from '../../token/token.service';
import { AuthenticationRequest } from "../../models/authenticationrequest";
import { AuthService } from "../../auth/auth.service";
import { AuthenticationResponse } from "../../models/authentication-response"; // Import interface

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginPageComponent {
  authRequest: AuthenticationRequest = { email: '', password: '' };
  errorMsg: Array<string> = [];
  successMsg: string | null = null;

  constructor(
    private router: Router,
    private authService: AuthService,
    private tokenService: TokenService
  ) {}
  //
  // login() {
  //   this.errorMsg = [];
  //   this.successMsg = null; // Reset thông báo thành công
  //
  //   this.authService.login(this.authRequest).subscribe({
  //     next: (res: AuthenticationResponse) => { // Sử dụng interface
  //       if (res.success) {
  //         this.successMsg = res.message; // Lưu thông báo thành công
  //         if (res.token) {
  //           this.tokenService.saveToken(res.token); // Lưu token bằng TokenService
  //
  //           // Lưu thông tin người dùng vào localStorage
  //           localStorage.setItem('userId', res.id.toString());
  //           if (res.username) {
  //             localStorage.setItem('username', res.username);
  //           }
  //
  //           // Chuyển hướng tới trang home
  //           this.router.navigate(['home']);
  //         } else {
  //           this.errorMsg.push('Token is missing in the response.'); // Thông báo lỗi nếu không có token
  //         }
  //       } else {
  //         this.errorMsg.push(res.message); // Thêm thông báo lỗi nếu không thành công
  //       }
  //     },
  //     error: (err: any) => {
  //       console.log(err);
  //       this.errorMsg.push(err.error.message || 'An error occurred.'); // Thông báo lỗi từ server
  //     }
  //   });
  // }

  login() {
    this.errorMsg = [];  // Reset thông báo lỗi trước khi đăng nhập
    this.successMsg = null;  // Reset thông báo thành công trước khi đăng nhập

    this.authService.login(this.authRequest).subscribe({
      next: (res: AuthenticationResponse) => {  // Sử dụng interface cho response
        if (res.success) {
          this.successMsg = res.message;  // Lưu thông báo thành công
          if (res.token) {
            this.tokenService.saveToken(res.token);  // Lưu token bằng TokenService

            // Lưu thông tin người dùng vào localStorage
            localStorage.setItem('userId', res.id.toString());
            if (res.username) {
              localStorage.setItem('username', res.username);
            }

            // Chuyển hướng tới trang home sau khi đăng nhập thành công
            this.router.navigate(['home']);
          } else {
            this.errorMsg.push('Token is missing in the response.');  // Thông báo lỗi nếu không có token
          }
        } else {
          this.errorMsg.push(res.message);  // Thêm thông báo lỗi nếu đăng nhập không thành công
        }
      },
      error: (err: any) => {
        console.log(err);
        this.errorMsg.push(err.error?.message || 'Người dùng không tồn tại .');  // Hiển thị lỗi từ server hoặc lỗi mặc định
      }
    });
  }


  register() {
    this.router.navigate(['register']); // Chuyển hướng đến trang đăng ký
  }
}
