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
    this.needService.getNeeds()
      .subscribe(needs => this.needs = needs.slice(0, 4));
  }
}