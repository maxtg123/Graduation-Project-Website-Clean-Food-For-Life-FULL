import {Component, ViewEncapsulation} from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/auth.service';
import { RegistrationRequest } from '../../models/registration-request';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  encapsulation: ViewEncapsulation.None

})
export class RegisterComponent {
  // Đối tượng chứa dữ liệu form
  registerRequest: RegistrationRequest = { username: '', email: '', password: '' };
  errorMsg: Array<string> = []; // Biến lưu các lỗi trả về
  successMsg: string = '';      // Biến lưu thông báo thành công

  constructor(private router: Router, private authService: AuthService) {}
// Hàm xử lý đăng ký
  register() {
    // Reset lại thông báo trước khi thực hiện đăng ký
    this.errorMsg = [];
    this.successMsg = '';

    // Gọi AuthService để thực hiện đăng ký
    this.authService.register(this.registerRequest).subscribe({
      next: (response) => {
        // Nếu đăng ký thành công, hiển thị thông báo và điều hướng
        if (response && response) {
          this.successMsg = 'Đăng ký tài khoản thành công. Vui lòng kiểm tra email để nhận OTP.';
          setTimeout(() => {
            this.router.navigate(['activate-account']);
          }, 5000); // Điều hướng sau 5 giây
        } else {
          // Nếu đăng ký không thành công, hiển thị lỗi
          this.errorMsg.push('Đăng ký không thành công. Vui lòng thử lại.');
        }
      },
      error: (err: any) => {
        console.log(err);
        // Hiển thị lỗi từ server, nếu có
        this.errorMsg = err.error?.validationErrors || ['Đăng ký không thành công. Vui lòng thử lại.'];
      },
    });
  }


  // Hàm điều hướng đến trang đăng nhập
  login() {
    this.router.navigate(['login']);
  }

  // Hàm điều hướng đến trang đăng nhập khi người dùng nhấp vào liên kết
  goToLogin() {
    this.router.navigate(['login']);
  }
}
