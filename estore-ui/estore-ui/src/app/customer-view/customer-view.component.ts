import { Component, OnInit } from '@angular/core';
import { Product } from 'src/Product';
import { ProductService } from '../product.service';
@Component({
  selector: 'app-customer-view',
  templateUrl: './customer-view.component.html',
  styleUrls: ['./customer-view.component.css']
})
export class CustomerViewComponent implements OnInit {
  
  constructor(private productservice:ProductService) { }
  selectedProduct?:Product;
  ngOnInit(): void {
    this.getProducts()
  }
  products:Product[]=[];

  onSelect(product:Product):void{
    this.selectedProduct=product;
  }

  getProducts():void{
    this.productservice.getProducts().subscribe(products=>this.products = products)
  }
  

}
