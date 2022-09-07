import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashComponent } from './admin-dash/admin-dash.component';
import { CreateMenuComponent } from './create-menu/create-menu.component';
import { MaterialModule } from '../material/material.module';
import { MatIconModule } from '@angular/material/icon';
import { ViewMenuComponent } from './view-menu/view-menu.component';


@NgModule({
  declarations: [
    AdminDashComponent,
    CreateMenuComponent,
    ViewMenuComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    MaterialModule
  ]
})
export class AdminModule { }
