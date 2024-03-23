// login.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

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
    private location: Location
  ) {}
  
  ngOnInit(): void {
    

  }
  getUser(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.loginService.getUser(id)
      .subscribe(user => this.user = user);
  }
  login(username: string, password: string):void {
    //this.getUser();
    //wait time?
    localStorage.setItem("username", username);

    //this.loginService.updateUser();
    this.loginService.setUsername(username);

    if(username && password == "admin"){
      window.location.href="http://localhost:4200/admin"
      
    }
    else{
      window.location.href="http://localhost:4200/dashboard"
    }
  }
}