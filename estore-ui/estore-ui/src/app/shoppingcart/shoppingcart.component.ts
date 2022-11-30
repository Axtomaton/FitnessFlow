import { Component, OnInit } from '@angular/core';
import { Product } from 'src/Product';
import { ProductService } from '../product.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-shoppingcart',
  templateUrl: './shoppingcart.component.html',
  styleUrls: ['./shoppingcart.component.css']
})
export class ShoppingcartComponent implements OnInit {

  constructor(private userservice:UserService) { }

  shoppingcart:Array<Product> = []
  totalcost:number=0
 
  ngOnInit(): void {
    this.getCart();
    this.getTotal();
  }

  addToCart(id:Product):void{
    this.userservice.addtoCart(id, this.userservice.returnusername())
  }

  getCart():void{
    this.shoppingcart=this.userservice.returnCart();
  }

  getTotal():void{
    let d = 0
    this.shoppingcart.forEach((element) => d += element.Price)

    d = parseFloat(d.toFixed(2));
    this.totalcost = d;
  }

  removeItemFromCart(thisproduct:Product):void{
    const index = this.shoppingcart.indexOf(thisproduct, 0);
    if (index > -1) {
       this.shoppingcart.splice(index, 1);
    }
    this.totalcost -= thisproduct.Price
    this.totalcost = parseFloat(this.totalcost.toFixed(2))

  }
  }