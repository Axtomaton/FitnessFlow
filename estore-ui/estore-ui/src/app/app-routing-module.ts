import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ShoppingcartComponent } from './shoppingcart/shoppingcart.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { AboutPageComponent } from './about-page/about-page.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { AddProductPageComponent } from './add-product-page/add-product-page.component';
import { ProductDetailCustomerComponent } from './product-detail-customer/product-detail-customer.component';
import { CustomerViewComponent } from './customer-view/customer-view.component'

export const routes: Routes = [
  {path: 'shoppingcart', component: ShoppingcartComponent},
  {path: 'login', component:LoginComponent},
  {path: 'about', component:AboutPageComponent},
  {path: 'signup',component:SignupComponent},
  {path: 'detail/:id',component:ProductDetailComponent},
  {path: 'newProduct',component:AddProductPageComponent},
  {path: 'customer/:id',component:ProductDetailCustomerComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [ShoppingcartComponent, LoginComponent, SignupComponent,AboutPageComponent]