import {Component, OnInit} from '@angular/core';
import {Product} from "../../models/product";
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../../auth/product.service";

@Component({
  selector: 'app-shop-details',
  templateUrl: './shop-details.component.html',
  styleUrls: ['./shop-details.component.css']
})
export class ShopDetailsComponent implements OnInit {
  product: Product | null = null;

  constructor(
      private route: ActivatedRoute,
      private productService: ProductService
) {}
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const productIdString = params.get('id'); // Get the product ID as a string
      if (productIdString) {
        const productId = Number(productIdString); // Convert string to number
        if (!isNaN(productId)) { // Check if the conversion was successful
          this.productService.getProductById(productId).subscribe(
              (data) => {
                this.product = data; // Store the product data in the variable
                console.log('Product Image URL:', this.product?.imageUrl);
              },
              (error) => {
                console.error('Error fetching product details:', error);
              }
          );
        } else {
          console.error('Invalid product ID:', productIdString);
        }
      }
    });
  }
}
