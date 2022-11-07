import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { ProductService } from './product.service';
import { Product } from 'src/Product';
import { User } from './User';
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

  private loggedinuser:User | undefined 
  private product:Product| undefined
  userLogin(user:string,pass:string,phone:string):void{
    const  body:userInformation={
        Username: user,
        Password: pass,
        phoneNumber: phone
      }
      this.http.post<User>(this.baseUrl+"login",body,this.httpOptions).subscribe(user=>{
          if(user !=null){
            this.loggedinuser=user
            this.router.navigate(['/customerView']);
          }
      });
  }

  addtoCart(id:number,username:string):void{
        this.http.get(this.baseUrl+"addToCart?Username="+username+"&ProductID="+id).subscribe()
  }
  removefromcart(id:number,username:string):void{
    this.http.get(this.baseUrl+"removeFromCart?Username="+username+"&ProductID="+id).subscribe()
  }
  returnusername():string{
    if(this.loggedinuser!=null){
      return this.loggedinuser.username
    }
    return ""
  }

  userSignup(first:string,last:string,phone:string,username:string,password:string):void{
    const body:User={
    firstName:first,
    lastName:last,
    phoneNumber:phone,
    username:username,
    password:password,
    cart:new Array<number>
    }
    this.http.post<User>(this.baseUrl+"signup",body,this.httpOptions).subscribe(user=>{
      if(user!=null){
        this.loggedinuser=user
        this.router.navigate(['/customerView']);
      }
    })
  }

  getLoggedInUser():User | null{
    if(this.loggedinuser !=null){
      return this.loggedinuser
    }
    return null;
  }
  taketosignuppage():void{
    this.router.navigate(["/signup"])
  }

  returnCart():Array<Number>{
    if(this.loggedinuser!=null){
      const  body:userInformation={
        Username: this.loggedinuser.username,
        Password: this.loggedinuser.password,
        phoneNumber: this.loggedinuser.phoneNumber
      }
      this.http.post<User>(this.baseUrl+"login",body,this.httpOptions).subscribe(user=>{
        this.loggedinuser=user
      })
      return this.loggedinuser.cart
    }
    else{
      return []
    }
  }
  returntotal():Number{
    let total:number=0
    if(this.loggedinuser!=null){
      const  body:userInformation={
        Username: this.loggedinuser.username,
        Password: this.loggedinuser.password,
        phoneNumber: this.loggedinuser.phoneNumber
      }
      this.http.post<User>(this.baseUrl+"login",body,this.httpOptions).subscribe(user=>{
        this.loggedinuser=user
      })
      for(let i=0;i<this.loggedinuser.cart.length;i++){
        this.productservice.getProduct(Number(this.loggedinuser.cart[i])).subscribe((prod: Product | null | undefined)=>{
          if(prod!=null){
            this.product=prod
          }
        })
        if(this.product!=null){
          total=total+this.product?.Price
        }
      }
    }
    return total
  }

  logout():void{
    this.loggedinuser=undefined;
    this.router.navigate([""])
  }
  
}

interface userInformation{
  Username:string,
  Password:string,
  phoneNumber:string
}

