import { Component, OnInit } from '@angular/core';
import { RequestService } from '../request.service';
import { HelpRequest } from '../request';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-request-detail',
  templateUrl: './request-detail.component.html',
  styleUrls: ['./request-detail.component.css']
})
export class RequestDetailComponent  implements OnInit{
  request: HelpRequest | undefined;
  constructor(
    private route: ActivatedRoute,
    private location: Location,
    private requestService:RequestService
  ) {}


  ngOnInit(): void {
    this.getRequest();
  }

  getRequest() : void{
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.requestService.getRequest(id)
      .subscribe(request => this.request = request);
  }
  goBack(): void {
    this.location.back();
  }
  //needs to be completed
  clearRequest() : void {

  }
}
