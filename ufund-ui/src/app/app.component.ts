import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { User } from './user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Tour of Needs';
  userId: number = 0;
  constructor(){
  }
  logout(){
    this.userId = 0;
    window.location.href="http://localhost:4200/login";
  }

}
2