import { Component, OnInit } from '@angular/core';
import { Product } from 'src/Product';

@Component({
  selector: 'app-product-lists',
  templateUrl: './product-lists.component.html',
  styleUrls: ['./product-lists.component.css']
})
export class ProductListsComponent implements OnInit {

  constructor() { }
  selectedProduct?:Product;
  ngOnInit(): void {
  }
  products:Product[]=[
      {"id":31,"Name":"Product 29","Type":"This is Type 23","Instructor":"Generic Instructor 23","Room_Number":"0635-G23","Available":true,"Price":76.89},
      {"id":32,"Name":"Product 30","Type":"This is Type 23","Instructor":"Generic Instructor 23","Room_Number":"0635-G23","Available":true,"Price":76.89},
      {"id":33,"Name":"Product 31","Type":"This is Type 23","Instructor":"Generic Instructor 23","Room_Number":"0635-G23","Available":true,"Price":76.89},
      {"id":34,"Name":"Product 32","Type":"This is Type 23","Instructor":"Generic Instructor 23","Room_Number":"0635-G23","Available":true,"Price":76.89},
      {"id":36,"Name":"Product 33","Type":"This is Type 23","Instructor":"Generic Instructor 23","Room_Number":"0635-G23","Available":true,"Price":76.89},
      {"id":35,"Name":"Product 34","Type":"This is Type 23","Instructor":"Generic Instructor 23","Room_Number":"0635-G23","Available":true,"Price":76.89},
      {"id":36,"Name":"Product 35","Type":"This is Type 23","Instructor":"Generic Instructor 23","Room_Number":"0635-G23","Available":true,"Price":76.89},
      {"id":37,"Name":"Product 36","Type":"This is Type 23","Instructor":"Generic Instructor 23","Room_Number":"0635-G23","Available":true,"Price":76.89},
      {"id":38,"Name":"Product 37","Type":"This is Type 23","Instructor":"Generic Instructor 23","Room_Number":"0635-G23","Available":true,"Price":76.89},
      {"id":39,"Name":"Product 38","Type":"This is Type 23","Instructor":"Generic Instructor 23","Room_Number":"0635-G23","Available":true,"Price":76.89},
      {"id":40,"Name":"Product 39","Type":"This is Type 23","Instructor":"Generic Instructor 23","Room_Number":"0635-G23","Available":true,"Price":76.89},
      {"id":41,"Name":"Product 40","Type":"This is Type 23","Instructor":"Generic Instructor 23","Room_Number":"0635-G23","Available":true,"Price":76.89},
      {"id":42,"Name":"Product 41","Type":"This is Type 23","Instructor":"Generic Instructor 23","Room_Number":"0635-G23","Available":true,"Price":76.89},
      {"id":43,"Name":"Product 42","Type":"This is type 23","Instructor":"Generic Instructor 23","Room_Number":"0635-G23","Available":true,"Price":76.89}
]

  onSelect(product:Product):void{
    this.selectedProduct=product;
  }

}

