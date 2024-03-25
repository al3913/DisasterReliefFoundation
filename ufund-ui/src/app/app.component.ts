import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Tour of Needs';
  constructor(private cookie: CookieService){

  }

  

  logout(){
    window.location.href="http://localhost:4200/login";
  }

}
2