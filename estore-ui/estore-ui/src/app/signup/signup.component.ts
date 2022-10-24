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

  doSignUp(user:String,pass:String,repss:String):void{
    if(pass==repss){
      this.userservice.userSignup()
    }
    else{
      this.router.navigateByUrl('');
    }
      
  }

}
