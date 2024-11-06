import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { FormsModule } from "@angular/forms";
import { RegisterComponent } from './pages/register/register.component';
import { ActivateAccountComponent } from './pages/activate-account/activate-account.component';
import { CodeInputModule } from 'angular-code-input';
import { HttpClientModule } from "@angular/common/http";
import { IndexComponent } from './module/index/index.component';
import { HomeComponent } from './module/home/home.component';
import {CommonModule, NgOptimizedImage} from "@angular/common";
import {ProfileComponent} from "./module/profile/profile.component";
import {RouterLink} from "@angular/router";
import { CartComponent } from './module/cart/cart.component';
import {CarouselModule} from "primeng/carousel";
import { ShopDetailsComponent } from './module/shop-details/shop-details.component';
import {ToastrModule} from "ngx-toastr";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";


@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    RegisterComponent,
    ActivateAccountComponent,
    IndexComponent,
    HomeComponent,
    CartComponent,
    ShopDetailsComponent,





  ],
    imports: [
        CommonModule,
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        CodeInputModule,
        HttpClientModule,
        NgOptimizedImage,
        ProfileComponent,
        RouterLink,
        CarouselModule,
        BrowserAnimationsModule, // Thêm dòng này
        ToastrModule.forRoot()   // Thêm dòng này

    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
