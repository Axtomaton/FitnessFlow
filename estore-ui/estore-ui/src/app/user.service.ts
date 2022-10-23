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
  userLogin(user:String,pass:String):void{
      
    const  body:userInformation={
        Username: user,
        Password: pass,
      }
      console.log(this.http.post(this.baseUrl,body,this.httpOptions).subscribe(isAdmin=>{
        if(isAdmin){
          this.router.navigate(['/adminView'])
        }
        else{
          this.router.navigate(['/customerView'])
        }
      }));
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

}

interface userInformation{
  Username:String,
  Password:String,
}