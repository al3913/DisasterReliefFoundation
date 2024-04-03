import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Need } from '../need';
import { NeedService } from '../need.service';
import { LoginComponent } from '../login/login.component';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-need-detail',
  templateUrl: './need-detail.component.html',
  styleUrls: [ './need-detail.component.css' ]
})
export class NeedDetailComponent implements OnInit {
  need: Need | undefined;
  user: string | null = null;


  constructor(
    private route: ActivatedRoute,
    private needService: NeedService,
    private loginService:LoginService,
    private location: Location
  ) {
    this.user = this.loginService.getWhoYouAre();
  }

  ngOnInit(): void {
    this.getNeed();
  }

  getNeed(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.needService.getNeed(id)
      .subscribe(need => this.need = need);
  }

  goBack(): void {
    this.location.back();
  }
  save(): void{
    if (this.need) { //Checks if hero exists
      this.needService.updateNeed(this.need)
      .subscribe(() => this.goBack());
    }
  }
}