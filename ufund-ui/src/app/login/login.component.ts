import { Component } from '@angular/core';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private loginService:LoginService) {}

  login(username: string, password: string):void {
    window.location.href="http://localhost:4200/dashboard"
  }

}
