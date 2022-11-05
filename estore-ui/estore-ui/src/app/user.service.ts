import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { ProductService } from './product.service';
import { User } from 'User';
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

  userSignup(first:string,last:string,phone:string,username:string,password:string):void{
    const body:User={
    firstName:first,
    lastName:last,
    phoneNumber:phone,
    Username:username,
    Password:password,
    cart:new Array<Number>
    }
    this.http.post<User>(this.baseUrl+"signup",body,this.httpOptions).subscribe(user=>{
      if(user!=null){
        this.loggedinuser=user
        this.router.navigate(['/customerView']);
      }
    })
    // this.router.navigate(['customerView'])
  }

  getLoggedInUser():User | null{
    if(this.loggedinuser !=null){
      return this.loggedinuser
    }
    return null;
  }

  logout():void{
    this.loggedinuser=undefined;
    console.log(this.loggedinuser)
  }
  
}

interface userInformation{
  Username:string,
  Password:string,
  phoneNumber:string
}

