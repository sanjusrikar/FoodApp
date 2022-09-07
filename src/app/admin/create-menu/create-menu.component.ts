import { Component, OnInit } from '@angular/core';
import { map, shareReplay } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { Food } from 'src/app/model/food';
import { FoodServiceService } from 'src/app/service/food-service.service';
import { Menu } from 'src/app/model/menu';
import { AdminServiceService } from 'src/app/service/admin-service.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-menu',
  templateUrl: './create-menu.component.html',
  styleUrls: ['./create-menu.component.css']
})
export class CreateMenuComponent implements OnInit {
  /** Based on the screen size, switch from standard to one column per row */
  foods : Food[]
  items : Food[]
  menu : Menu
  id : number
  
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
  .pipe(
    map(result => result.matches),
    shareReplay()
  );

  constructor(private breakpointObserver: BreakpointObserver,
    private foodService : FoodServiceService,
    private adminService : AdminServiceService,
    private router : Router) {}
  
  // create menu 
  // then add items to menu
    ngOnInit(): void {
      console.log(localStorage.getItem('menuId'))
      this.getAllFoods()
      this.id =  Number(localStorage.getItem('menuId'))     
      this.items = []
    
    }
  
  
  private getAllFoods() {
    this.foodService.getAllFoods().subscribe(data => {
      this.foods = data;
    })
  }
  createMenu(){
    // get id of admin' to create menu
    // to add we need menu id...   
    console.log(this.id)
    this.adminService.addItemsToMenu(this.items,this.id).subscribe(data=>{
      this.router.navigateByUrl('admin/ViewMenu')
    })

  }
  add(food:Food){
    this.items.push(food)
    this.foods = this.foods.filter(obj => {
      return obj !== food}
      );
  }
  remove(food:Food){
    this.foods.push(food)
    this.items = this.items.filter(obj => {
      return obj !== food}
      );
  }





}
