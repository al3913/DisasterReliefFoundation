import {Component} from '@angular/core';
import {
  NgIf,
  NgFor,
  UpperCasePipe,
} from '@angular/common';
import {FormsModule} from '@angular/forms';

import {Need} from '../need';
import { NeedService } from '../need.service';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-needs',
  templateUrl: './needs.component.html',
  styleUrls: ['./needs.component.css'],
})

export class NeedsComponent {
  needs: Need[] = [];
  //selectedHero?: Hero;
  constructor(private needService:NeedService, private messageService: MessageService) {}

  // onSelect(hero: Hero): void {
  //   this.selectedHero = hero;
  //   this.messageService.add('HeroesComponent: Selected hero id=${hero.id}');
  // }

  getNeeds(): void{
    this.needService.getNeeds().subscribe(needs => this.needs = needs)
  }

  ngOnInit(): void{
    this.getNeeds();
  }

  add(name: string) : void {
    name = name.trim();
    if (!name) {
      return;
    }
    this.needService.addNeed({name} as Need)
    .subscribe(need => {
      this.needs.push(need);
    })
  }

  delete(need: Need): void {
    this.needs = this.needs.filter(h => h !== need);
    this.needService.deleteNeed(need.id).subscribe();
  }

  
}