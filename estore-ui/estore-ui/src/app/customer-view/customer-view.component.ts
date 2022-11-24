import { Component, OnInit } from '@angular/core';
import { Product } from 'src/Product';
import { ProductService } from '../product.service';
import { UserService } from '../user.service';
import { User } from '../User';
@Component({
  selector: 'app-customer-view',
  templateUrl: './customer-view.component.html',
  styleUrls: ['./customer-view.component.css']
})
export class CustomerViewComponent implements OnInit {
  
  constructor(private productservice:ProductService,private userservice:UserService) { }
  selectedProduct?:Product;
  loggedinUser?:User | null;
  ngOnInit(): void {
    this.getProducts()
    this.loggedinUser=this.userservice.getLoggedInUser()
  }
  products:Product[]=[];

  onSelect(product:Product):void{
    this.selectedProduct=product;
  }

  getProducts():void{
    this.productservice.getProducts().subscribe(products=>this.products = products)
  }
  
}
