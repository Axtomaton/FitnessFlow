import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-order-confirmation-page',
  templateUrl: './order-confirmation-page.component.html',
  styleUrls: ['./order-confirmation-page.component.css']
})
export class OrderConfirmationPageComponent implements OnInit {

  constructor(private userservice:UserService, private router:Router) { }

  ngOnInit(): void {
    // this.userservice.clearCart()
    setTimeout(() => {
      this.router.navigate(['']);
  }, 4000);  //5s
  }

}
