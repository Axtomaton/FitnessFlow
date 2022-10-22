import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ShoppingcartComponent } from './shoppingcart/shoppingcart.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { AboutPageComponent } from './about-page/about-page.component';

import { HomePageComponent } from './home-page/home-page.component';
import { OrderConfirmationPageComponent } from './order-confirmation-page/order-confirmation-page.component';

import { ProductDetailComponent } from './product-detail/product-detail.component';
import { AddProductPageComponent } from './add-product-page/add-product-page.component';
import { ProductListsComponent } from './product-lists/product-lists.component';


export const routes: Routes = [
  {path: 'shoppingcart', component: ShoppingcartComponent},
  {path: 'login', component:LoginComponent},
  {path: 'about', component:AboutPageComponent},
  {path: 'signup',component:SignupComponent},

  {path: 'home', component:HomePageComponent},
  {path: 'orderconfirmed', component:OrderConfirmationPageComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'detail/:id', component:ProductDetailComponent},
  {path: 'newProduct', component:AddProductPageComponent},
  {path: 'adminView', component:ProductListsComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
