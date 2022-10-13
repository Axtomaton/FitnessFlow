import { Component, OnInit } from '@angular/core';
import { Product } from '../Product';
import { PRODUCTS } from '../mock-products';
@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {


  products:Product[]=PRODUCTS;

  pro=PRODUCTS[2];

  constructor() { }

  ngOnInit(): void {
  }

}
