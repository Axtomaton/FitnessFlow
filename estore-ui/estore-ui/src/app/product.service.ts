import { Injectable } from '@angular/core';
import { Product } from 'src/Product';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
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
  
      console.error(error); // log to console instead
      // TODO: better job of transforming error for user consumption
      console.error(`${operation} failed: ${error.message}`);
      // Let the app keep running by returning an empty result.
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
  updateHero(product: Product): Observable<any> {
    return this.http.put(this.baseUrl+'/product', product, this.httpOptions).pipe(
      tap(_ => console.error(`updated hero id=${product.id}`)),
      catchError(this.handleError<any>('updateHero'))
    );
  }



}
