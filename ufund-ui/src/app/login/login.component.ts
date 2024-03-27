// login.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { AppComponent } from '../app.component';

import { User } from '../user';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  user: User | undefined;

  constructor(
    private route: ActivatedRoute,
    private loginService: LoginService,
    private location: Location,
    private appComponent : AppComponent
  ) {}
  
  ngOnInit(): void {
    

  }

  login(username: string, password: string):void {
    
         if(username && password == "admin"){
          window.location.href="http://localhost:4200/admin"
          
        }
        else{
          window.location.href="http://localhost:4200/dashboard"
        }
      }
}