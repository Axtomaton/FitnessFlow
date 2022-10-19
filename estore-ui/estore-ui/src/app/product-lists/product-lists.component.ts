import { Component, OnInit } from '@angular/core';
import { Product } from 'src/Product';
import { ProductService } from '../product.service';
@Component({
  selector: 'app-product-lists',
  templateUrl: './product-lists.component.html',
  styleUrls: ['./product-lists.component.css']
})
export class ProductListsComponent implements OnInit {

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
