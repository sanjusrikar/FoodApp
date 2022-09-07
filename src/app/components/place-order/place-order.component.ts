import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { Food } from 'src/app/model/food';
import { FoodServiceService } from 'src/app/service/food-service.service';
import { Router } from '@angular/router';
import { MatSliderChange } from '@angular/material/slider';
import { MatDialog } from '@angular/material/dialog';
import { CartComponent } from '../cart/cart.component';

@Component({
  selector: 'place-order',
  templateUrl: './place-order.component.html',
  styleUrls: ['./place-order.component.css']
})
export class PlaceOrderComponent implements OnInit {

  foods: Food[];
  items: Food[];


  constructor(private breakpointObserver: BreakpointObserver, private foodService: FoodServiceService,
    private router: Router , private dialog : MatDialog) { }
  ngOnInit(): void {

    this.getAllFoods();
    this.items = []
    

  }

  addToCart(food: Food) 
  {
    this.items.push(food)
    // add food to cart
    //this.router.navigateByUrl('/cart/' + this.orderId);
  }
  test() {
    

    this.dialog.open(CartComponent,{
      data: this.items
    });
    // this.router.navigateByUrl('/cart/' + this.orderId);
  }
  removeFromCart(food:Food){
    this.items = this.items.filter(obj => {
      return obj !== food}
      );

  }
  getQuantity(event: MatSliderChange, food : Food) {
    if ( event.value != null){
      food.choosenQuantity = event.value;
    }
    
  }

  private getAllFoods() {
    this.foodService.getAllFoods().subscribe(data => {
      this.foods = data;
    })
  }


}
