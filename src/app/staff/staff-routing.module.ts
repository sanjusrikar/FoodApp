import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from '../components/cart/cart.component';
import { NavComponent } from '../components/nav/nav.component';
import { OrdersListComponent } from '../components/orders-list/orders-list.component';
import { PlaceOrderComponent } from '../components/place-order/place-order.component';

const routes: Routes = [{
  path: '',
  component: NavComponent,
  children : [
    { path: 'orders', component: OrdersListComponent },
    { path: 'placeOrder', component: PlaceOrderComponent },
    { path: 'cart/:orderId', component: CartComponent },
    {path:'',redirectTo:'placeOrder',pathMatch:"full"}
  ]

},


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StaffRoutingModule { }
