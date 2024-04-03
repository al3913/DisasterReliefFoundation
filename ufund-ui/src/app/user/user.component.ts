import { Component } from '@angular/core';
import { NeedService } from '../need.service';
import { LoginService } from '../login.service';
import {Need} from '../need';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  needs: Need[] = [];
  userId: number = 0;
  constructor(private needService:NeedService, private loginService:LoginService){}

  logout(){
    this.userId = 0;
    this.loginService.logout;
    localStorage.clear();
    window.location.href="http://localhost:4200/login";
  }
}
