import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/Product';
import { ProductService } from '../product.service';
import { Location } from '@angular/common';
import { UserService } from '../user.service';

@Component({
  selector: 'app-product-detail-customer',
  templateUrl: './product-detail-customer.component.html',
  styleUrls: ['./product-detail-customer.component.css']
})
export class ProductDetailCustomerComponent implements OnInit {

  product:Product | undefined

  constructor(
    private productservice:ProductService,
    private route: ActivatedRoute,
    private location: Location,
    private userservice:UserService
    ) { }


  ngOnInit(): void {
    this.getProduct();
  }

  goBack():void{
    this.location.back();
  }
  getProduct():void{
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.productservice.getProduct(id)
      .subscribe(product => this.product = product);
  }

  addToCart(id:number): void {
    this.userservice.addToCart(id);
    this.goBack();
  }
}
