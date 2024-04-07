import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { NeedService } from '../need.service';
import { User } from '../user';
import { LoginService } from '../login.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-funding-basket',
  templateUrl: './funding-basket.component.html',
  styleUrls: [ './funding-basket.component.css' ]
})
export class FundingBasketComponent implements OnInit {
  needs: Need[] = [];
  total : number = 0;
  user : Observable<User> | undefined;
  constructor(private needService: NeedService, private loginService : LoginService) { }

  ngOnInit(): void {
    this.getNeeds();
    

  }

  getNeeds(): void {
    this.loginService.getFundingBasketNeeds()
      .subscribe(needs => this.needs = needs);
  }

  delete(need: Need): void {
    this.needs = this.needs.filter(h => h !== need);
    this.loginService.removeFromBasket(need.id).subscribe(needs => this.needs);
  }


  checkout(){
    this.loginService.basketCheckout().subscribe(needs=>this.needs);
    this.needs = this.needs.filter(h => h !== h);
  }

  getTotal(){
    this.user = this.loginService.getUser(this.loginService.getWhoYouAre());
    //want to be able to access the user's total here somehow
  }
}