import { Component } from '@angular/core';
import { User } from './user';
import { LoginService } from './login.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Disaster Relief Foundation';
  userId: number = 0;
  x : boolean = false;
  
  constructor(private loginService : LoginService){}
  user = this.loginService.getWhoYouAre();
  logout(){
    this.userId = 0;
    this.loginService.logout;
    localStorage.clear();
    window.location.href="http://localhost:4200/login";
  }
}
