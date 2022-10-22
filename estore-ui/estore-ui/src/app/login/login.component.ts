import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userservice:UserService) { }

  ngOnInit(): void {
  }

  doLogin(user:String,pass:String):void{
    this.userservice.userLogin(user,pass)
  }
}
