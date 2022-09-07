import { Component, Inject, OnInit } from '@angular/core';
import { Food } from 'src/app/model/food';
import { UserServiceService } from 'src/app/service/user-service.service';
import  {MAT_DIALOG_DATA} from '@angular/material/dialog'
import { FoodOrder } from 'src/app/model/food-order';
import { Router } from '@angular/router';
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  
  order_Id: number;
  foods : Food[]
  displayedColumns = ["Name" , "Quantity" , "Price"]

  constructor(private userService : UserServiceService, private router : Router,
    @Inject(MAT_DIALOG_DATA) public items: Food[]) { }

  ngOnInit(): void {
    this.foods = this.items;
    this.order_Id = Number(localStorage.getItem('orderId'));//somehow get this lol nuub
    console.log(localStorage.getItem('orderId'))
    
    
  }
  confirmOrder(){
    this.userService.createCart(this.foods,this.order_Id).subscribe(data =>{
      this.userService.readyOrder(this.order_Id)
      this.userService.sendMail(this.order_Id).subscribe(data=>{
        console.log(data)
      })
      if(data.order_Id >= 1){
        this.router.navigateByUrl('/staff/orders')
      } 
    });
  }

}
