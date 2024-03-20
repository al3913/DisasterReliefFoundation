// app.component.ts
import { Component } from '@angular/core';
import { LoginService } from './login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  constructor(private loginService:LoginService) {}
  title = 'Tour of Needs';
  username :string = "";
  logout(): void{
    window.location.href="http://localhost:4200/login";
  }

  updateUsername() { 
    console.log('AppComponent Username: ' + this.username);
  } 
}