import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductsListI } from 'src/app/models/productsList.interface';
import { ApiService } from 'src/app/services/api/api.service';




@Component({
  selector: 'app-addaccount',
  templateUrl: './addaccount.component.html',
  styleUrls: ['./addaccount.component.css']
})
export class AddaccountComponent implements OnInit {
  
  constructor(private activerouter:ActivatedRoute , private api:ApiService, private router:Router, 
    private http:HttpClient ) {
    
  }
  accounts!: ProductsListI[];
  
ngOnInit(): void {
  
  this.activerouter.params.subscribe(params => {
    this.api.getAllAccounts(params['idNumber']).subscribe(data =>{
      console.log(data);
      this.accounts = data;    
     
    }
  )})
   
 }
 transfers(){
  this.router.navigate(['transaction']);
 }
 Viewtransfers(accountNumber:any){
  this.router.navigate(['customers',accountNumber]);
 }
}

 
 
 




