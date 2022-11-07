import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Product } from 'src/Product';
import { ProductService } from '../product.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-shoppingcart',
  templateUrl: './shoppingcart.component.html',
  styleUrls: ['./shoppingcart.component.css'],
  changeDetection:ChangeDetectionStrategy.OnPush
})
export class ShoppingcartComponent implements OnInit {

  constructor(private userservice:UserService,private productservice:ProductService) { }

  shoppingcart:Array<Number>|undefined = []
  totalcost:Number=0
  product:Product | undefined
  ngOnInit(): void {
    this.shoppingcart=this.userservice.returnCart()
    this.totalcost=this.userservice.returntotal()
    }

    removefromcart(item:Number){
      if(this.userservice.getLoggedInUser()!=null){
        this.userservice.removefromcart(Number(item),this.userservice.returnusername())
      }
    }
  }
