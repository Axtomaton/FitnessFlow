import { Component, OnInit } from '@angular/core';
import { debounceTime, distinctUntilChanged, Observable, Subject, switchMap } from 'rxjs';
import { Product } from 'src/Product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.css']
})
export class ProductSearchComponent implements OnInit {

  products$!:Observable<Product[]>;

  private searchTerms = new Subject<string>()

  constructor(private productService:ProductService) { }

  search(term:string):void{
    this.searchTerms.next(term)
  }

  ngOnInit(): void {
    this.products$=this.searchTerms.pipe(
        debounceTime(300),
        distinctUntilChanged(),
        switchMap((term:string)=>this.productService.searchProduct(term))
    );
  }

}