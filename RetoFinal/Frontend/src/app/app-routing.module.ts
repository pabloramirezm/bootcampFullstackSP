import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountsComponent } from './screens/accounts/accounts.component';
import { AddaccountComponent } from './screens/addaccount/addaccount.component';
import { AddcustomerComponent } from './screens/addcustomer/addcustomer.component';
import { CustomersComponent } from './screens/customers/customers.component';
import { DashboardComponent } from './screens/dashboard/dashboard.component';
import { LoginComponent } from './screens/login/login.component';
import { TransactionComponent } from './screens/transaction/transaction.component';
import { EditcustomerComponent } from './screens/editcustomer/editcustomer.component';
// import { AuthGuard  } from '@auth0/auth0-angular';
import { AuthGuard} from './guards/auth.guard';

const routes: Routes = [
  // {path:'' , redirectTo:'login' , pathMatch:'full'},
  {path:'' , redirectTo:'dashboard' , pathMatch:'full'},
  {path:'login' , component:LoginComponent},
  {path:'dashboard' , component:DashboardComponent,canActivate:[AuthGuard]},
  {path:'accounts/:idNumber' , component:AccountsComponent,canActivate:[AuthGuard]},
  {path:'addaccount/:idNumber' , component:AddaccountComponent,canActivate:[AuthGuard]},
  {path:'editcustomer/:idNumber' , component:EditcustomerComponent,canActivate:[AuthGuard]},
  {path:'customers/:accountNumber' , component:CustomersComponent,canActivate:[AuthGuard]},
  {path:'addcustomer' , component:AddcustomerComponent,canActivate:[AuthGuard]},
  {path:'transaction' , component:TransactionComponent,canActivate:[AuthGuard]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [LoginComponent,DashboardComponent,AccountsComponent,AddaccountComponent,
 CustomersComponent,AddcustomerComponent,TransactionComponent,EditcustomerComponent]
//  