import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { Location } from '@angular/common';
import { Product } from 'src/Product';
import { Rating } from '../Rating';
@Component({
  selector: 'app-add-product-page',
  templateUrl: './add-product-page.component.html',
  styleUrls: ['./add-product-page.component.css']
})
export class AddProductPageComponent implements OnInit {

  constructor(private productservice:ProductService,
              private location:Location) { }

  ngOnInit(): void {
  }
  product:Product | undefined
  add(name:string,instructor:string,type:string,price:string, room:string):void{
      name=name.trim();
      instructor=instructor.trim();
      type=type.trim();
      price=price.trim();
      room=room.trim();

      this.product={
        Name:name,
        Type:type,
        Instructor:instructor,
        Room_Number:room,
        Price:Number(price),
        id:1,
        Available:true,
        Ratings:new Array<Rating>

      };
      this.productservice.addProduct(this.product).subscribe(()=>this.location.back())

  }

  goBack():void{
    this.location.back()
  }
}
