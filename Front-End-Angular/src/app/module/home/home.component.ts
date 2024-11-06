import { Component, ElementRef, OnInit, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from '../../token/token.service';
import { ProductService } from "../../auth/product.service";
import { Product } from "../../models/product";
import {ToastrService} from "ngx-toastr";
import {CartService} from "../../auth/cartservice";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, AfterViewInit {

  userId: number | null = null;
  products: Product[] = []; // Mảng để chứa sản phẩm
  cartItems: Product[] = []; // Mảng chứa sản phẩm trong giỏ hàng

  constructor(
    private elRef: ElementRef,
    private router: Router,
    private tokenService: TokenService,
    private toastr: ToastrService,
    private cartService: CartService,
    private productService: ProductService // Tiêm ProductService
  ) {
  }

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
    this.router.navigate(['profile'], {state: {userId: userId}});
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

  addToCart(product: Product) {
    if (this.userId) {
      this.cartService.addToCart(product, this.userId).subscribe(
        () => {
          this.toastr.success(`${product.name} đã được thêm vào giỏ hàng!`);
        },
        (error: any) => {
          console.error('Lỗi khi thêm vào giỏ hàng:', error);
          this.toastr.error('Lỗi khi thêm vào giỏ hàng');
        }
      );
    } else {
      this.toastr.warning('Vui lòng đăng nhập để thêm sản phẩm vào giỏ hàng.');
      this.router.navigate(['login']);
    }
  }

}
