import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './helper/auth-guard';
import { LoginGuard } from './helper/login-guard';
const routes: Routes = [
  { path: 'login',
   component: LoginComponent,},
  {
    path: 'staff',
    canActivate:[LoginGuard],
    loadChildren : ()=>
      import('./staff/staff.module').then((m)=>m.StaffModule)
  },
  {    
    path: 'admin',
    canActivate: [AuthGuard,LoginGuard] ,
  loadChildren : ()=>
    import('./admin/admin.module').then((m)=>m.AdminModule)
  },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
