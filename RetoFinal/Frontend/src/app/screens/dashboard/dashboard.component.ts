import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../services/api/api.service';
import { Router } from '@angular/router';
import { customersListI } from '../../models/customersList.interface';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { ResponseI } from 'src/app/models/response.interface';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private api:ApiService, private router:Router, private http:HttpClient) {}
  customers!: customersListI[];

ngOnInit(): void {
  this.api.getAllCustomers().subscribe(data =>{
    
  this.customers = data;
  })
}

 editCustomer(idNumber: any){
   this.router.navigate(['editcustomer',idNumber]);
  
 }
 addCustomer(){
  this.router.navigate(['addcustomer']);
 }
 
 



}
