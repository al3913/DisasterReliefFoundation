import { Component } from '@angular/core';
import { HelpRequest } from '../request';

import { RequestService } from '../request.service';
@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent {
  requests: HelpRequest[] = [];

  ngOnInit(): void{
    this.getRequests();
    
  }

  constructor(private requestService:RequestService){}
  getRequests() : void{
    this.requestService.getRequests().subscribe(requests => this.requests = requests)
  }
}
