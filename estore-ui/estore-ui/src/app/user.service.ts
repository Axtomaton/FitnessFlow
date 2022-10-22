import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient,private router: Router) { }

  private baseUrl='http://localhost:8080/user/'

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private cart:Array<Number>=[];

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
  }
  returnCart():Array<Number>{
    return this.cart;
  }

}

interface userInformation{
  Username:String,
  Password:String,
}
