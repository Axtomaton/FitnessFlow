import { Component, OnInit, Input} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/Product';
import { ProductService } from '../product.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  product:Product | undefined

  constructor(
    private productservice:ProductService,
    private route: ActivatedRoute,
    private location: Location
    ) { }


  ngOnInit(): void {
    this.getProduct();
  }

  delete(product:Product){
    if(this.product){
      this.productservice.deleteProduct(this.product.id).subscribe(()=>this.goBack());
    }
  }
  goBack():void{
    this.location.back();
  }
  getProduct():void{
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.productservice.getProduct(id)
      .subscribe(product => this.product = product);
  }

  save(): void {
    if (this.product) {
      this.productservice.updateProduct(this.product)
        .subscribe(() => this.goBack());
    }
  }
  
}
