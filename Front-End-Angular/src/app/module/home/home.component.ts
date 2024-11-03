// import { Component, ElementRef, OnInit, AfterViewInit } from '@angular/core';
// import { Router } from '@angular/router';
// import { TokenService } from '../../token/token.service';
// import { ProductService } from "../../auth/product.service";
// import { Product } from "../../models/product";
//
// @Component({
//   selector: 'app-home',
//   templateUrl: './home.component.html',
//   styleUrls: ['./home.component.css']
// })
// export class HomeComponent implements OnInit, AfterViewInit {
//
//   userId: number | null = null;
//   products: Product[] = []; // Array to hold featured products
//
//   constructor(
//     private elRef: ElementRef,
//     private router: Router,
//     private tokenService: TokenService,
//     private productService: ProductService // Inject ProductService
//   ) { }
//
//   ngOnInit() {
//     const navigation = this.router.getCurrentNavigation();
//     if (navigation?.extras.state) {
//       const state = navigation.extras.state as { userId: number };
//       this.userId = state.userId;
//     } else {
//       this.userId = Number(localStorage.getItem('userId'));
//     }
//
//     // Khởi tạo mảng sản phẩm
//     this.products = [
//       {
//         id: 1,
//         imageUrl: 'assets/img/featured/feature-1.jpg', // Thay 'src' bằng 'imageUrl'
//         name: 'Cat 1',
//         description: 'Description for Cat 1',
//         price: 100,
//         category: 'Category 1',
//         title: 'DANH MUC 1'
//       },
//       {
//         id: 2,
//         imageUrl: 'assets/img/featured/feature-2.jpg',
//         name: 'Cat 2',
//         description: 'Description for Cat 2',
//         price: 200,
//         category: 'Category 2',
//         title: 'DANH MUC 2'
//       },
//       {
//         id: 3,
//         imageUrl: 'assets/img/featured/feature-3.jpg',
//         name: 'Cat 3',
//         description: 'Description for Cat 3',
//         price: 300,
//         category: 'Category 3',
//         title: 'DANH MUC 3'
//       },
//       {
//         id: 4,
//         imageUrl: 'assets/img/featured/feature-4.jpg',
//         name: 'Cat 4',
//         description: 'Description for Cat 4',
//         price: 400,
//         category: 'Category 4',
//         title: 'DANH MUC 4'
//       },
//       {
//         id: 5,
//         imageUrl: 'assets/img/featured/feature-5.jpg',
//         name: 'Cat 5',
//         description: 'Description for Cat 5',
//         price: 500,
//         category: 'Category 5',
//         title: 'DANH MUC 5'
//       }
//     ];
//   }
//
//   isCategoriesVisible = false;
//
//   toggleCategories() {
//     this.isCategoriesVisible = !this.isCategoriesVisible;
//   }
//
//   // Lấy ảnh full
//
//   logout() {
//     this.tokenService.logout(); // Gọi phương thức logout để xóa token
//     this.router.navigate(['login']); // Chuyển hướng về trang đăng nhập
//   }
//
//   goToProfile() {
//     // Lấy userId từ localStorage
//     const userId = localStorage.getItem('userId');
//     // Chuyển hướng đến trang Profile với userId
//     this.router.navigate(['profile'], { state: { userId: userId } });
//   }
//
//   ngAfterViewInit(): void {
//     const heroItems = this.elRef.nativeElement.querySelectorAll('.set-bg');
//
//     // Lặp qua từng phần tử và thiết lập hình nền
//     heroItems.forEach((item: HTMLElement) => {
//       const bg = item.getAttribute('data-setbg');
//       if (bg) {
//         item.style.backgroundImage = `url(${bg})`;
//       }
//     });
//   }
// }


import { Component, ElementRef, OnInit, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from '../../token/token.service';
import { ProductService } from "../../auth/product.service";
import { Product } from "../../models/product";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit {

  userId: number | null = null;
  products: Product[] = []; // Mảng để chứa sản phẩm

  constructor(
    private elRef: ElementRef,
    private router: Router,
    private tokenService: TokenService,
    private productService: ProductService // Tiêm ProductService
  ) { }

  ngOnInit() {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      const state = navigation.extras.state as { userId: number };
      this.userId = state.userId;
    } else {
      this.userId = Number(localStorage.getItem('userId'));
    }

    this.productService.getProducts().subscribe(
      (data: Product[]) => {
        this.products = data.map(product => {
          return {
            ...product,
            imageUrl: `http://localhost:8080${product.imageUrl}` // Gán đường dẫn đầy đủ
          };
        });
        console.log('Danh sách sản phẩm:', this.products); // Log để kiểm tra dữ liệu
      },
      (error) => {
        console.error('Lỗi khi lấy danh sách sản phẩm:', error);
      }
    );
  }

  isCategoriesVisible = false;

  toggleCategories() {
    this.isCategoriesVisible = !this.isCategoriesVisible;
  }

  logout() {
    this.tokenService.logout(); // Gọi phương thức logout để xóa token
    this.router.navigate(['login']); // Chuyển hướng về trang đăng nhập
  }

  goToProfile() {
    const userId = localStorage.getItem('userId');
    this.router.navigate(['profile'], { state: { userId: userId } });
  }

  ngAfterViewInit(): void {
    const heroItems = this.elRef.nativeElement.querySelectorAll('.set-bg');

    // Lặp qua từng phần tử và thiết lập hình nền
    heroItems.forEach((item: HTMLElement) => {
      const bg = item.getAttribute('data-setbg');
      if (bg) {
        item.style.backgroundImage = `url(${bg})`;
      }
    });
  }

}
