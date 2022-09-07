import { Component, OnInit } from '@angular/core';
import { Menu } from 'src/app/model/menu';
import { MenuServiceService } from 'src/app/service/menu-service.service';

@Component({
  selector: 'app-view-menu',
  templateUrl: './view-menu.component.html',
  styleUrls: ['./view-menu.component.css']
})
export class ViewMenuComponent implements OnInit {

  oid : number
  constructor(private menuService : MenuServiceService) { }
  menus : Menu[]
  displayedColumns = ["Foods"]
  ngOnInit(): void {
    this.getAllMenus();

  }
  private getAllMenus(){
    this.menuService.getAllMenu().subscribe(data=>{
      this.menus = data;
    })
  }
  deleteMenu(id:number){
    this.oid = Number(localStorage.getItem("menuId"))
    this.menuService.deleteMenu(this.oid).subscribe(data=>{
      console.log(data);
    })
  }

}
