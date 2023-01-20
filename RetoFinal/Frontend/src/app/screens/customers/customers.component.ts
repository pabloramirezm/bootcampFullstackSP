import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TransactionI } from 'src/app/models/transactions.interface';
import { ApiService } from 'src/app/services/api/api.service';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  
  constructor(private activerouter:ActivatedRoute , private api:ApiService, private router:Router, 
    private http:HttpClient ) {
    
  }
  transfers!: TransactionI[];
  
ngOnInit(): void {
  
  this.activerouter.params.subscribe(params => {
    this.api.getTransaction(params['accountNumber']).subscribe(data =>{
      console.log(data);
      this.transfers = data;    
     
    }
  )})
   
 }
 Return(){
  this.router.navigate(['dashboard']);
 }
 
}{

}
