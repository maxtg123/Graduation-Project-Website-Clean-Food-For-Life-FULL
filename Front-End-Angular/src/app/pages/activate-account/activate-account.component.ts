import {Component, ViewEncapsulation} from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.css'],
  encapsulation: ViewEncapsulation.None


})
export class ActivateAccountComponent {
  activationCode: string = '';
  submitted: boolean = false;
  isOkay: boolean = false;
  message: string = '';

  constructor(private router: Router, private authService: AuthService) {}

  onCodeCompleted(code: string) {
    this.activationCode = code; // Lưu mã kích hoạt nhập từ người dùng
  }

  activateAccount() {
    this.submitted = true; // Đánh dấu là đã gửi
    this.authService.activateAccount({ code: this.activationCode }).subscribe({
      next: () => {
        this.isOkay = true; // Đánh dấu kích hoạt thành công
      },
      error: (err: any) => {
        this.isOkay = false; // Đánh dấu kích hoạt thất bại
        this.message = err.error.message || 'Activation failed. Please try again.'; // Lấy thông điệp lỗi từ server
      },
    });
  }

  redirectToLogin() {
    this.router.navigate(['login']); // Chuyển hướng đến trang đăng nhập
  }
}
