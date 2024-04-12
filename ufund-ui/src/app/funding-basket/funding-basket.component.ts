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
  cost : number  = 0;
  user : Observable<User> | undefined;
  userObject : User | undefined;
  constructor(private needService: NeedService, private loginService : LoginService) { }

  ngOnInit(): void {
    this.getNeeds();
    this.getTotal();
    this.getBasketCost();
  }

  getNeeds(): void {
    this.loginService.getFundingBasketNeeds()
      .subscribe(needs => this.needs = needs);
  }

  getBasketCost() : number{
    var cartTotal = 0;
    for(var need of this.needs){
        cartTotal += need.cost;
    }
    return cartTotal;
  }

  delete(need: Need): void {
    this.needs = this.needs.filter(h => h !== need);
    this.loginService.removeFromBasket(need.id).subscribe(needs => this.needs);
  }


  checkout(){
    this.loginService.basketCheckout().subscribe(needs=>this.needs);
    this.needs = this.needs.filter(h => h !== h);
    this.getTotal();
  }

  getTotal(){
    this.loginService.getTotal(this.loginService.getWhoYouAre())
    .subscribe(total => this.total = total);
    //console.log("total" + this.total);
  
  }
}