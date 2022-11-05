import { Component, OnInit } from '@angular/core';
import { User } from 'User';
import { UserService } from '../user.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userservice:UserService) { }
  loggedInUser?: User | null

  ngOnInit(): void {
    this.loggedInUser=this.userservice.getLoggedInUser()
  }

  doLogin(user:string,pass:string,phone:string):void{
    if(user=="" || pass==""){
      return
    }
    else{
      this.userservice.userLogin(user,pass,phone)
    }
    
  }
}
