import { Component, OnInit } from '@angular/core';
import { User } from '../User';
import { UserService } from '../user.service';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  constructor(private userservice:UserService) { }
  loggedinuser?: User | null
  ngOnInit(): void {
    this.loggedinuser=this.userservice.getLoggedInUser()
  }

  logout():void{
    this.userservice.logout()
  }

}
