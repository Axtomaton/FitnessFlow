import { Injectable } from '@angular/core';
import { Product } from 'src/Product';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { ProductRating } from './ProductRating';
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient ) { }

  getProducts():Observable<Product[]>{
    return this.http.get<Product[]>(this.baseUrl+'products').pipe(tap(_=>console.error('fetched Products')),catchError(this.handleError<Product[]>('getProducts',[])))
  }
  private productsUrl='http://localhost:8080/admin/products'
  private baseUrl='http://localhost:8080/admin/'

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      console.error(error);
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getProduct(id:number):Observable<Product>{
    const url = `${this.baseUrl+'product'}/${id}`;
    return this.http.get<Product>(url).pipe(
      tap(_ => console.error(`fetched hero id=${id}`)),
      catchError(this.handleError<Product>(`getHero id=${id}`))
    );
  }
  updateProduct(product: Product): Observable<any> {
    return this.http.put(this.baseUrl+'/product', product, this.httpOptions).pipe(
      tap(_ => console.error(`updated Product id=${product.id}`)),
      catchError(this.handleError<any>('updateProduct'))
    );
  }
  searchProduct(term:string):Observable<Product[]>{
    if(!term.trim()){
      return this.getProducts();
    }
    return this.http.get<Product[]>(`${this.baseUrl}/?Name=${term}`).pipe(
      tap(x => x.length ?
        console.error(`found Products matching "${term}"`) :
        console.error(`no Products matching "${term}"`)),
     catchError(this.handleError<Product[]>('searchProduct', []))
    )
  }

  addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.baseUrl+'/product', product, this.httpOptions).pipe(
      tap((newHero: Product) => console.error(`added Products w/ id=${newHero.id}`)),
      catchError(this.handleError<Product>('addProduct'))
    );
  }

  deleteProduct(id: number): Observable<Product> {
    const url = `${this.baseUrl}/product/${id}`;
    return this.http.delete<Product>(url, this.httpOptions).pipe(
      tap(_ => console.error(`deleted Product id=${id}`)),
      catchError(this.handleError<Product>('deletedProduct'))
    );
  }
  AddRating(body:ProductRating):void{
     this.http.post<Product>(this.baseUrl+'/addRating',body,this.httpOptions).subscribe()
  }
}
