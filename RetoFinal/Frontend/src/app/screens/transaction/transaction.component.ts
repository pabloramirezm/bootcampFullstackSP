import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductsI } from 'src/app/models/products.interface';
import { AlertsService } from 'src/app/services/alertas/alerts.service';
import { ApiService } from 'src/app/services/api/api.service';
import { customerEditI } from 'src/app/models/customeredit.interface'
import { customersListI } from 'src/app/models/customersList.interface';
import { TransactionI } from 'src/app/models/transactions.interface';
import { FormDataService } from '../../services/formData/form-data.service';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit{
  formData1: FormGroup;
  formData2: FormGroup;
  NewTransaction!: TransactionI ;
  
constructor(private api:ApiService, private router:Router, private alerts:AlertsService,
  private formDataService: FormDataService  ){

  this.formData2 = new FormGroup({
    id: new FormControl(''),
    description : new FormControl  (''), 
    valueTransaction : new FormControl  (''), 
    movementType: new FormControl  (''),  
    accountNumber1: new FormControl  (''),
    accountNumber2: new FormControl  ('')
  });
  this.formData1 = this.formDataService.getFormData();
  // let id = this.formData1.value.id;
  }
  // customer!  : customersListI;
ngOnInit(): void {
  
}
  postForm(){
    Object.assign(this.formData2.value, this.NewTransaction);
    let accountNumber2 = this.formData2.value.accountNumber2;
      this.api.postTransaction(this.formData2.value, accountNumber2).subscribe(response =>{
          //  let response:ResponseI = responsex;
          //  console.log(this.formData2.value);
           console.log(response);
           console.log(response);
        
          if(response == 'Transacción realizada'){ 
            this.alerts.showSuccess('Transfer successful','Done');
            this.router.navigate(['dashboard']);
          }else{
            this.alerts.showError('Transfer cancel','Error');
          }
      })
      this.formDataService.setFormData2(this.formData2);
  }
  submitForm(){
    Object.assign(this.formData2.value, this.NewTransaction);
    let accountNumber1 = this.formData2.value.accountNumber1;
    let accountNumber2 = this.formData2.value.accountNumber2;
      this.api.postTransfers(this.formData2.value, accountNumber1, accountNumber2).subscribe(responsex =>{
          //  let response:ResponseI = responsex;
           console.log(this.formData2.value);
           console.log(responsex);
           console.log(responsex);
        
          if(responsex == 'Transacción realizada'){ 
            this.alerts.showSuccess('Transfer successful','Done');
            this.router.navigate(['dashboard']);
          }else{
            this.alerts.showError('Transfer cancel','Error');
          }
      })
      this.formDataService.setFormData2(this.formData2);
  }
  exit(){
    this.router.navigate(['dashboard']);
  }
}

