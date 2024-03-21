import { Component } from '@angular/core';
import { NeedService } from '../need.service';
import {Need} from '../need';
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  needs: Need[] = [];
  //selectedHero?: Hero;
  constructor(private needService:NeedService) {}
  getNeeds(): void{
    this.needService.getNeeds().subscribe(needs => this.needs = needs)
  }

  ngOnInit(): void{
    this.getNeeds();
    
  }
  add(name: string, cost: number, quantity : number, type : string) : void {
    name = name.trim();
    if (!name) {
      return;
    }
    if (!cost) {
      return;
    }
    if (!quantity) {
      return;
    }
    type = type.trim();
    if (!type) {
      return;
    }
    this.needService.addNeed({name, cost, quantity, type} as Need)
    .subscribe(need => {
      this.needs.push(need);
    })

  }
  delete(need: Need): void {
    this.needs = this.needs.filter(h => h !== need);
    this.needService.deleteNeed(need.id).subscribe();
  }
  convertToString( str:string){
      return parseInt(str);
  }
}
