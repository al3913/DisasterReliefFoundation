import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NeedsComponent } from './needs/needs.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NeedDetailComponent } from './need-detail/need-detail.component';
import { FundingBasketComponent } from './funding-basket/funding-basket.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import { RequestsComponent } from './requests/requests.component';
import { RequestsAdminComponent } from './requests-admin/requests-admin.component';
import { RequestDetailComponent } from './request-detail/request-detail.component';

const routes: Routes = [
  { path: 'needs', component: NeedsComponent },
  { path: 'dashboard', component: DashboardComponent},
  { path: 'funding-basket', component: FundingBasketComponent},
  { path: 'detail/:id', component: NeedDetailComponent},
  { path: 'login', component: LoginComponent },
  { path: 'admin', component: AdminComponent},
  { path: 'user',component:UserComponent},
  { path: 'requests', component:RequestsComponent},
  { path: 'requests-admin',component:RequestsAdminComponent},
  { path: 'mailbox/:id', component: RequestDetailComponent},
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }