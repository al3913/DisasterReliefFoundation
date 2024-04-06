import { Component } from '@angular/core';
import { HelpRequest } from '../request';
import { RequestService } from '../request.service';

@Component({
  selector: 'app-requests-admin',
  templateUrl: './requests-admin.component.html',
  styleUrls: ['./requests-admin.component.css']
})
export class RequestsAdminComponent {
  requests: HelpRequest[] = [];

  ngOnInit(): void{
    this.getRequests();
    
  }

  constructor(private requestService:RequestService){}
  getRequests() : void{
    this.requestService.getRequests().subscribe(requests => this.requests = requests)
  }
}
