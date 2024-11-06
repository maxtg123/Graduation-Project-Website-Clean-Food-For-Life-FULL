// import {Component, OnInit} from '@angular/core';
// import {Product} from "../../models/product";
// import {CartService} from "../../auth/cart.service";
// import {CartItem} from "../../models/CartItem";
// import {Observable} from "rxjs";
//
// @Component({
//   selector: 'app-shoping-cart',
//   templateUrl: './shoping-cart.component.html',
//   styleUrls: ['./shoping-cart.component.css']
// })
// export class ShopingCartComponent implements OnInit {
//   cartItems: Observable<any[]> = [];
//   total: number = 0;
//
//   constructor(private cartService: CartService) {}
//
//   ngOnInit(): void {
//     this.cartItems = this.cartService.getCartItems();
//     this.total = this.cartService.getTotal();
//   }
//
//   removeFromCart(item: CartItem) {
//     this.cartService.removeFromCart(item);
//     this.updateTotal();
//   }
//
//   updateQuantity(item: CartItem, quantity: number) {
//     this.cartService.updateQuantity(item, quantity);
//     this.updateTotal();
//   }
//
//   updateTotal() {
//     this.total = this.cartService.getTotal();
//   }
// }
