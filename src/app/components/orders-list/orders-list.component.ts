import { Component, OnInit } from '@angular/core';
import { OrdersServiceService } from 'src/app/service/orders-service.service';
import { FoodOrder } from '../../model/food-order';


@Component({
  selector: 'orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.css']
})
export class OrdersListComponent implements OnInit {

  orders: FoodOrder[];
  oid : number
  displayedColumns: string[] = ['order_id', 'name', 'email', 'status','total_price','timeStamp','Foods','actions'];
  constructor(private orderService : OrdersServiceService) { }

  ngOnInit(): void {
    this.getAllOrders();
  }

  private getAllOrders(){
    this.orderService.getAllOrders().subscribe(data =>{
      this.orders = data;
      this.orders.forEach(element => {
        let d = new Date(element.timeStamp);
        let l = new Date(d.getTime());
        element.timeStamp = l.toLocaleTimeString();
        this.orderService.getItemsForOrder(element.order_Id).subscribe(data1 => {
          element.foods = data1;
        })
      });
    })
  }

  deleteOrder(id : number){
   
    this.orderService.deleteOrder(id).subscribe(data=>{
      console.log(data);
    })
  }

}
