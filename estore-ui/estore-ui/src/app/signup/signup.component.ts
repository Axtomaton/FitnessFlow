import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userservice:UserService,
    private location:Location,
    private router:Router) { }

  ngOnInit(): void {
  }

  doSignUp(first:string,last:string,phone:string,user:string,pass:string,repss:string):void{
    if(pass==repss){
      this.userservice.userSignup(first,last,phone,user,pass)
    }
    else{
      this.router.navigateByUrl('');
    }
      
  }
  taketologin():void{
    this.router.navigate(['/login'])
  }

}
