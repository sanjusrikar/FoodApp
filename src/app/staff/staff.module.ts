import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StaffRoutingModule } from './staff-routing.module';
import { MaterialModule } from '../material/material.module';

@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    StaffRoutingModule,
    MaterialModule
  ]
})
export class StaffModule { }
