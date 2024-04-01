import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { NeedService } from '../need.service';
import { User } from '../user';
import { UserService } from '../user.service';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  needs: Need[] = [];
  users : User[] = [];

  constructor(private needService: NeedService, private loginService : LoginService) { }

  ngOnInit(): void {
    this.getNeeds();
    
    
  }

  getUsername(user : User) : string{
    return this.getUsername(user);
  }
  getNeeds(): void {
    this.needService.getNeeds()
      .subscribe(needs => this.needs = needs.slice(0, 4));
  }
  getUsers() : void{
    this.loginService.getUsers()
    .subscribe(users => this.users = users);
  }
}