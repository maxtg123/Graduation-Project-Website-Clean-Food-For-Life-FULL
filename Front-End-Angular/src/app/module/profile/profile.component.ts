import { Component, ElementRef, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { TokenService } from '../../token/token.service';
import { UserService } from '../../auth/user.service';
import { User } from '../../models/user';
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink,
    NgForOf,
    NgIf
  ],
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User | null = null; // Sử dụng kiểu User
  updateForm: Partial<User> = { // Dùng Partial để không bắt buộc trường password
    username: '',
    email: '',
    password:'',
    phone: '',
    address: ''
  };
  avatarFile: File | null = null;
  successMsg: string | null = null; // Thông báo thành công
  errorMsg: string[] = []; // Danh sách thông báo lỗi


  constructor(
    private elRef: ElementRef,
    private router: Router,
    private tokenService: TokenService,
    private userService: UserService
  ) {}

  ngOnInit() {
    const userId = localStorage.getItem('userId');
    if (userId) {
      this.userService.getUserById(userId).subscribe((userData) => {
        this.user = userData;
        this.populateFormWithUserData();
      }, error => {
        console.error("Failed to load user data", error);
      });
    }
  }

  populateFormWithUserData() {
    if (this.user) {
      this.updateForm.username = this.user.username;
      this.updateForm.email = this.user.email;
      // this.updateForm.password = this.user.password;
      this.updateForm.phone = this.user.phone;
      this.updateForm.address = this.user.address;
    }
  }
  //
  // updateUserProfile() {
  //   const userId = localStorage.getItem('userId');
  //   if (userId) {
  //     const updatedUserData: User = {
  //       id: this.user!.id, // Sử dụng `!` để cho TypeScript biết rằng user không null
  //       username: this.updateForm.username || this.user!.username, // Giữ giá trị cũ nếu không thay đổi
  //       email: this.updateForm.email || this.user!.email,
  //       password: this.updateForm.password || '', // Có thể bỏ qua nếu không muốn cập nhật
  //       phone: this.updateForm.phone || this.user!.phone,
  //       address: this.updateForm.address || this.user!.address
  //     };
  //
  //     this.userService.updateUser(userId, updatedUserData).subscribe(response => {
  //       this.successMsg = 'Profile updated successfully.'; // Hiển thị thông báo thành công
  //       console.log("Profile updated successfully", response);
  //     }, error => {
  //       this.errorMsg.push('Failed to update profile.'); // Thêm thông báo lỗi
  //       console.error("Failed to update profile", error);
  //     });
  //   }
  // }

  updateUserProfile() {
    const userId = localStorage.getItem('userId');
    if (userId) {
      // Tạo đối tượng cập nhật từ form và dữ liệu người dùng hiện tại
      const updatedUserData: User = {
        id: this.user!.id, // Sử dụng `!` vì bạn đã kiểm tra user != null
        username: this.updateForm.username || this.user!.username,
        email: this.updateForm.email || this.user!.email,
        password: this.updateForm.password || '', // Nếu không muốn cập nhật, có thể bỏ qua hoặc không gửi
        phone: this.updateForm.phone || this.user!.phone,
        address: this.updateForm.address || this.user!.address
      };


      // Gọi API để cập nhật thông tin người dùng
      this.userService.updateUser(userId, updatedUserData).subscribe({
        next: (response) => {
          this.successMsg = 'Hồ sơ đã được cập nhật thành công.'; // Thông báo thành công
          console.log("Profile updated successfully", response);
        },
        error: (error) => {
          this.errorMsg.push('Failed to update profile.'); // Thông báo lỗi
          console.error("Failed to update profile", error);
        }
      });
    } else {
      this.errorMsg.push('User not found. Please log in again.'); // Thông báo nếu không tìm thấy userId
    }
  }

  handleAvatarChange(event: any) {
    this.avatarFile = event.target.files[0];
  }

  uploadAvatar() {
    const id = localStorage.getItem('userId'); // Lấy userId từ localStorage

    if (this.avatarFile) { // Kiểm tra xem có file ảnh hay không
      this.userService.updateUserAvatar(id, this.avatarFile).subscribe(
        updatedUser => {
          this.user = updatedUser; // Cập nhật thông tin người dùng sau khi upload
          this.avatarFile = null; // Reset file ảnh sau khi cập nhật
          this.successMsg = 'Avatar updated successfully.'; // Thông báo thành công
          console.log("Avatar updated successfully", updatedUser);
        },
        error => {
          this.errorMsg.push('Failed to update avatar.'); // Thêm thông báo lỗi
          console.error("Failed to update avatar", error); // In ra lỗi nếu có
        }
      );
    } else {
      console.warn("No avatar file selected"); // Thông báo nếu không có file
      this.errorMsg.push('No avatar file selected.'); // Thêm thông báo lỗi
    }
  }

  // uploadAvatar() {
  //   const id = localStorage.getItem('userId'); // Lấy userId từ localStorage
  //
  //   if (this.avatarFile) { // Kiểm tra xem có file ảnh hay không
  //     const formData = new FormData();
  //     formData.append('profileImage', this.avatarFile); // Thêm tệp ảnh vào FormData
  //
  //     this.userService.updateUserAvatar(id, formData).subscribe(
  //       (updatedUser: User) => {
  //         this.user = updatedUser; // Cập nhật thông tin người dùng sau khi upload
  //         this.avatarFile = null; // Reset file ảnh sau khi cập nhật
  //         this.successMsg = 'Avatar updated successfully.'; // Thông báo thành công
  //         console.log("Avatar updated successfully", updatedUser);
  //       },
  //       error => {
  //         this.errorMsg.push('Failed to update avatar.'); // Thêm thông báo lỗi
  //         console.error("Failed to update avatar", error); // In ra lỗi nếu có
  //       }
  //     );
  //   } else {
  //     console.warn("No avatar file selected"); // Thông báo nếu không có file
  //     this.errorMsg.push('No avatar file selected.'); // Thêm thông báo lỗi
  //   }
  // }


  logout() {
    // Xử lý logout
  }

  goToProfile() {
    // Chuyển đến trang profile
  }

  updateProfile() {
    this.updateUserProfile(); // Gọi hàm để cập nhật thông tin
  }
}
