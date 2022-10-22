import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ShoppingcartComponent } from './shoppingcart/shoppingcart.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { AboutPageComponent } from './about-page/about-page.component';
import { RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing-module';
import { HomePageComponent } from './home-page/home-page.component';
import { OrderConfirmationPageComponent } from './order-confirmation-page/order-confirmation-page.component';


@NgModule({
  declarations: [
    AppComponent,
    ShoppingcartComponent,
    SignupComponent,
    LoginComponent,
    AboutPageComponent,
    HomePageComponent,
    OrderConfirmationPageComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
