import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './plantillas/header/header.component';
import { FooterComponent } from './plantillas/footer/footer.component';

import {ReactiveFormsModule, FormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { EditcustomerComponent } from './screens/editcustomer/editcustomer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { AddaccountComponent} from './screens/addaccount/addaccount.component'
import { AddcustomerComponent } from './screens/addcustomer/addcustomer.component';
import { AuthModule } from '@auth0/auth0-angular';
import { TransactionComponent } from './screens/transaction/transaction.component';
import { CustomersComponent } from './screens/customers/customers.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    routingComponents,
    EditcustomerComponent,
    AddcustomerComponent,
    AddaccountComponent,
    TransactionComponent,
    CustomersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule, 
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule, 
    ToastrModule.forRoot(),
    AuthModule.forRoot({
      domain: 'dev-3boc8r0ft5jamaur.us.auth0.com',
      clientId: '0P3HuC8aRv8NYaOfN9CbZ8naBdvq9dbF',
      cacheLocation:'localstorage',
      useRefreshTokens: true
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
