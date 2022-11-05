import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { ProductService } from './product.service';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient,private router: Router,
    private productservice:ProductService) { }

  private baseUrl='http://localhost:8080/user/'

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private cart:Array<Number>=[];
  private total:number=0;
  userLogin(user:string,pass:string,phone:string):void{
    const  body:userInformation={
        Username: user,
        Password: pass,
        Phone: phone
      }
      console.log(phone)
      this.http.post<User>(this.baseUrl+"login",body,this.httpOptions).subscribe(user=>{
        console.log(user.FirstName)
      });
  }

  userSignup():void{
    this.router.navigate(['customerView'])
  }
  addToCart(id:number):void{
    this.cart.push(id)
    this.productservice.getProduct(id).subscribe((product)=>{
      this.total+=product.Price;
    });
  }
  returnCart():Array<Number>{
    return this.cart;
  }
  returnTotal():number{
    return this.total;
  }
  clearCart():void{
    this.cart=[]
    this.total=0;
  }

}

interface userInformation{
  Username:string,
  Password:string,
  Phone:string
}

interface User{
     FirstName:string;
     LastName:string;
     PhoneNumber:string;
     username:string;
     password:string;
     Cart:Array<Number>;
}