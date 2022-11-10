import { Component, OnInit } from '@angular/core';
import { Product } from 'src/Product';
import { ProductService } from '../product.service';
import { User } from '../User';
import { UserService } from '../user.service';
@Component({
  selector: 'app-product-lists',
  templateUrl: './product-lists.component.html',
  styleUrls: ['./product-lists.component.css']
})
export class ProductListsComponent implements OnInit {

  constructor(private productservice:ProductService,private userservice:UserService) { }
  selectedProduct?:Product;
  loggedinuser:User| undefined | null
  ngOnInit(): void {
    this.getProducts()
    this.loggedinuser=this.userservice.getLoggedInUser()
  }
  products:Product[]=[];

  onSelect(product:Product):void{
    this.selectedProduct=product;
  }

  getProducts():void{
    this.productservice.getProducts().subscribe(products=>this.products = products)
  }
  

}
