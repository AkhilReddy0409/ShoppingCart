import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ProductModel } from '../models/product-model';

@Injectable({
  providedIn: 'root'
})
export class CatalogService {

  constructor(private http: HttpClient) { }

  getAllProducts(){
    return this.http.get<ProductModel[]>(`http://localhost:8888/product/getAllProducts`);
  }

  getProduct(id: number){
    return this.http.get<ProductModel>(`http://localhost:8888/product/${id}`);
  }
  // getProductByName(name: number){
  //   return this.http.get<ProductModel>(`http://localhost:8888/product/get/${name}`);
  // }

  addProduct(product: ProductModel){
    return this.http.post<ProductModel>(`http://localhost:8888/product/addProduct`, product);
  }

  deleteProduct(id: number){
    return this.http.delete<ProductModel>(`http://localhost:8888/product/deleteProduct/${id}`);
  }

  editProduct(product: ProductModel){
    return this.http.put<ProductModel>(`http://localhost:8888/product/update`, product);
  }

}
