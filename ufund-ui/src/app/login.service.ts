import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  username : string = "";
  password : string = "";

  

  updateUsername(username : string) {
    this.username = username;
  }
  updatePassword(password : string) {
    this.password = password;
  }

}