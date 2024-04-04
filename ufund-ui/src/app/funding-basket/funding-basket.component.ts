import { Component, OnInit } from '@angular/core';
import { Need } from '../need';
import { NeedService } from '../need.service';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-funding-basket',
  templateUrl: './funding-basket.component.html',
  styleUrls: [ './funding-basket.component.css' ]
})
export class FundingBasketComponent implements OnInit {
  needs: Need[] = [];

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
    this.loginService.removeFromBasket(need.id).subscribe(needs => this.needs = needs);
  }
}