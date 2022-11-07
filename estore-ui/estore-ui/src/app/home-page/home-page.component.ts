import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../User';
@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private userservice:UserService) { }
  loggedinuser?: User | null

  ngOnInit(): void {
    this.loggedinuser=this.userservice.getLoggedInUser()
  }

  logout():void{
    this.userservice.logout()
  }

}
