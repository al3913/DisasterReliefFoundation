import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { AppComponent } from './app.component';
import { NeedsComponent } from './needs/needs.component';
import { NeedDetailComponent } from './need-detail/need-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { AppRoutingModule } from './app-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { FundingBasketComponent } from './funding-basket/funding-basket.component';
import { HttpClientModule } from '@angular/common/http';
import { AdminComponent } from './admin/admin.component';
import { NeedSearchComponent } from './need-search/need-search.component';
import { UserComponent } from './user/user.component';
import { RequestsComponent } from './requests/requests.component';
import { RequestsAdminComponent } from './requests-admin/requests-admin.component';
import { RequestDetailComponent } from './request-detail/request-detail.component';
import { LeaderboardComponent } from './leaderboard/leaderboard.component';

@NgModule({
  declarations: [
    AppComponent, 
    NeedsComponent,
    NeedDetailComponent,
    MessagesComponent,
    DashboardComponent,
    FundingBasketComponent,
    AdminComponent,
    NeedSearchComponent,
    UserComponent,
    RequestsComponent,
    RequestsAdminComponent,
    RequestDetailComponent,
    LeaderboardComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
