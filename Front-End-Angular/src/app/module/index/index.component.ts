import {Component, ElementRef} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent {

  isCategoriesVisible = false;

  toggleCategories() {
    this.isCategoriesVisible = !this.isCategoriesVisible;
  }

  constructor(private elRef: ElementRef) {}

  ngAfterViewInit() {
    // Lấy tất cả các phần tử có lớp 'set-bg'
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
