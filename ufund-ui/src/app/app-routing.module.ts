import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NeedsComponent } from './needs/needs.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NeedDetailComponent } from './need-detail/need-detail.component';
import { FundingBasketComponent } from './funding-basket/funding-basket.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { AppComponent } from './app.component';

const routes: Routes = [
  { path: 'needs', component: NeedsComponent },
  { path: 'dashboard', component: DashboardComponent},
  { path: 'funding-basket', component: FundingBasketComponent},
  { path: 'detail/:id', component: NeedDetailComponent},
  { path: 'login', component: LoginComponent },
  { path: 'admin', component: AdminComponent},
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }