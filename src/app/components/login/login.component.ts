import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Menu } from 'src/app/model/menu';
import { User } from 'src/app/model/user';
import { AdminServiceService } from 'src/app/service/admin-service.service';
import { UserServiceService } from 'src/app/service/user-service.service';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router,
    private userService: UserServiceService,
    private formBuilder: FormBuilder,
    private adminService : AdminServiceService) { }

  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  user: User
  menu : Menu


  ngOnInit(): void {
    this.user = {
      Id: 0,
      email: '',
      password: ''
    }
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    })
  }
  get f() { return this.loginForm.controls; }
  onSubmit() {
    this.submitted = true;
    if (this.loginForm.invalid) {
      return;
    }
    this.loading = true;
    this.user.email = this.f.email.value
    this.user.password = this.f.password.value

    this.userService.login(this.user).subscribe(
      data => {
        localStorage.setItem('currentUserId',data.r.id);
        localStorage.setItem('currentUserRole',data.r.role);
        switch(data.r.role){
          case "STAFF":
            this.userService.placeOrder({
              email: 'sanjaythamma@gmail.com',
              name: 'sanjay',
            },data.r.id).subscribe(data=>{
              localStorage.setItem("orderId",data.r.order_Id);
              this.router.navigateByUrl('/staff')
            })
            break;
          case "BRANCH_MANAGER":
            this.adminService.createMenu({Id:0,foods:[]},data.r.id).subscribe(data=>{
              localStorage.setItem("menuId",data.r.id);
              this.router.navigateByUrl('/admin')
            })
            
            break;
          default:
            this.router.navigateByUrl('/')
        }
      },
      error =>{
        console.log(error)
        this.router.navigateByUrl('/')
      }
      
    )

  }

}
