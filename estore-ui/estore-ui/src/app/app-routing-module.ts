import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ShoppingcartComponent } from './shoppingcart/shoppingcart.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ContactPageComponent } from './contact-page/contact-page.component';
import { AboutPageComponent } from './about-page/about-page.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';

export const routes: Routes = [
  {path: 'shoppingcart', component: ShoppingcartComponent},
  {path: 'login', component:LoginComponent},
  {path: 'contact', component:ContactPageComponent},
  {path: 'about', component:AboutPageComponent},
  {path: 'signup',component:SignupComponent},
  {path: 'detail/:id',component:ProductDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
// export const routingComponents = [ShoppingcartComponent, LoginComponent, SignupComponent,ContactPageComponent,AboutPageComponent]