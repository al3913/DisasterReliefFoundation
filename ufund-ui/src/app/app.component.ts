import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { User } from './user';
import { LoginService } from './login.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Tour of Needs';
  userId: number = 0;
  
  constructor(private loginService : LoginService){
  }
    logout(){
    this.userId = 0;
    this.loginService.logout;
    window.location.href="http://localhost:4200/login";
  }

}
