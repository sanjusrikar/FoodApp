import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../components/login/login.component';
import { AdminDashComponent } from './admin-dash/admin-dash.component';
import { CreateMenuComponent } from './create-menu/create-menu.component';
import { ViewMenuComponent } from './view-menu/view-menu.component';

const routes: Routes = [{
  path: '',
  component: AdminDashComponent,
  children : [
    { path: 'createMenu', component: CreateMenuComponent },
    { path: 'ViewMenu', component: ViewMenuComponent },
    {path:'',redirectTo:'createMenu',pathMatch:"full"}
  ]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
