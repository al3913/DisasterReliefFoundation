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
  constructor(private cookie: CookieService){
  }

  ngOnInit(): void{
    this.userId = +this.cookie.get("User");
    console.log("Get cookie " + this.userId);
  }

  setCookie(userId : string){
    this.userId = +userId; // changes the userId param into a number then stores
    this.cookie.set("User", userId);
    console.log("Set Cookie " + userId);
  }

  logout(){
    this.userId = 0;
    this.cookie.delete("User");
    window.location.href="http://localhost:4200/login";
  }

}
2