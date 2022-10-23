import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-shoppingcart',
  templateUrl: './shoppingcart.component.html',
  styleUrls: ['./shoppingcart.component.css']
})
export class ShoppingcartComponent implements OnInit {

  constructor(private userservice:UserService) { }

  shoppingcart:Array<Number> = []
  totalcost:number=0

  ngOnInit(): void {
    this.getCart();
  }

  addToCart(id:number):void{
    this.userservice.addToCart(id)

  }

  getCart():void{
    this.shoppingcart=this.userservice.returnCart();
  }

}