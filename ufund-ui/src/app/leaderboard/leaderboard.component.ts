import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { LoginService } from '../login.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.css']
})
export class LeaderboardComponent implements OnInit{
  users: Observable<User>[] = [];
  constructor(
    private loginService : LoginService
  ){}
  ngOnInit(): void {
    this.getUsers();
    console.log(this.users.length);
  }
  
  getUsers(){
    this.loginService.getUsers()
    .subscribe(users => users = users);
  }
  getUsername() : string{
    return this.loginService.getUsername();
  }

}
