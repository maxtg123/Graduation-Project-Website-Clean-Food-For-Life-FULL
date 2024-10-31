import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from "./pages/register/register.component";
import { ActivateAccountComponent } from "./pages/activate-account/activate-account.component";
import { IndexComponent } from "./module/index/index.component";
import { HomeComponent } from "./module/home/home.component";
import { AuthGuard } from './auth/auth.guard';
import {LoginPageComponent} from "./pages/login-page/login-page.component";
import {ProfileComponent} from "./module/profile/profile.component";

 // Đảm bảo đường dẫn chính xác

const routes: Routes = [
  // Bỏ AuthGuard nếu không cần thiết
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
   { path: 'profile', component: ProfileComponent,canActivate: [AuthGuard] },
  {
    path: '',
    redirectTo: 'index',
    pathMatch: 'full'
  },

  {
    path: 'login',
    component: LoginPageComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'activate-account',
    component: ActivateAccountComponent
  },
  {
    path: 'index',
    component: IndexComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
