import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/Product';
import { ProductService } from '../product.service';
import { Location } from '@angular/common';
import { UserService } from '../user.service';
import { User } from '../User';
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

    loggedinuser?: User | null
  ngOnInit(): void {
    this.getProduct();
    this.loggedinuser=this.userservice.getLoggedInUser()
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
    if(this.loggedinuser!=null){
      this.userservice.addtoCart(id,this.loggedinuser.username)
      this.goBack();
    }
    else{
      this.userservice.taketosignuppage()
    }

  }
  goback():void{
    this.goBack();
  }
}
