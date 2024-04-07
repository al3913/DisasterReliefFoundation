import { Component, OnInit } from '@angular/core';
import { HelpRequest } from '../request';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { RequestService } from '../request.service';
import { User } from '../user';

import { LoginService } from '../login.service';
@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent  implements OnInit{
  requests: HelpRequest[] = [];
  

  constructor(private requestService:RequestService, private loginService:LoginService,
    private location:Location){}

  ngOnInit(): void{
    this.getRequests();
  }
  getRequests() : void{
    this.requestService.getRequests().subscribe(requests => this.requests = requests)
  }
  goBack(): void {
    this.location.back();
  }
  sendRequest(body : string) : void {
    //const x = this.loginService.getWhoYouAre();
    this.requestService.addRequest({body} as HelpRequest)
    .subscribe(request => {
      this.requests.push(request);
    })
    this.goBack();
  }
}
