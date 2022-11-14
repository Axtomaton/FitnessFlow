import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/Product';
import { ProductService } from '../product.service';
import { UserService } from '../user.service';
import { Rating } from '../Rating';
import { ProductRating } from '../ProductRating';
import { Location } from '@angular/common';

@Component({
  selector: 'app-add-review-page',
  templateUrl: './add-review-page.component.html',
  styleUrls: ['./add-review-page.component.css']
})
export class AddReviewPageComponent implements OnInit {

  constructor( 
    private productservice:ProductService,
    private route: ActivatedRoute,
    private userservice: UserService,
    private location: Location,
    )
     {
      }
   id:number |undefined
   product:Product | undefined

  ngOnInit(): void {
    this.id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.productservice.getProduct(this.id)
      .subscribe(product => this.product = product);
  }


  AddRating(rating:number,review:string):void{
    if(this.product!=null){
      const R:Rating={
        RatingInNumber:rating,
        Review:review,
        Username:this.userservice.returnusername()
      }
      const body:ProductRating={
        Product:this.product,
        Ratings:R
      }
      this.productservice.AddRating(body);
      this.location.back()
    }
  }

}


