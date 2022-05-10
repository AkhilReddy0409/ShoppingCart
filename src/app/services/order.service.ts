import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ItemModel } from '../models/item-model';
import { OrderModel } from '../models/order-model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  checkOut(order: OrderModel){
    return this.http.post<OrderModel>(`http://localhost:8888/order/add`, order);
  }

  getRecentOrder(userId: number){
    return this.http.get<OrderModel>(`http://localhost:8888/order/recent/${userId}`);
  }

  getRecentItems(userId: number){
    return this.http.get<ItemModel[]>(`http://localhost:8888/order/recent/items/${userId}`);
  }

  getAllOrders(userId: number){
    return this.http.get<OrderModel[]>(`http://localhost:8888/order/all/${userId}`);
  }

  getOrder(orderId:number){
    return this.http.get<OrderModel>(`http://localhost:8888/order/get/${orderId}`);
  }

  getItems(orderId: number){
    return this.http.get<ItemModel>(`http://localhost:8888/order/items/${orderId}`);
  }

}
