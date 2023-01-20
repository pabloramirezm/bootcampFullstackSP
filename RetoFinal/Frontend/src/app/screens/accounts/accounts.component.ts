import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductsI } from 'src/app/models/products.interface';
import { AlertsService } from 'src/app/services/alertas/alerts.service';
import { ApiService } from 'src/app/services/api/api.service';
import { customerEditI } from 'src/app/models/customeredit.interface'
import { customersListI } from 'src/app/models/customersList.interface';
import { FormDataService } from '../../services/formData/form-data.service';


@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html', 
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit{
  formData: FormGroup;
  formData1: FormGroup;
  NewAccount!: ProductsI ;
  // customer!  : customersListI;
 

constructor(private api:ApiService, private router:Router, private alerts:AlertsService,
  private formDataService: FormDataService ){   
    
    this.formData1 = new FormGroup({
      id: new FormControl(''),
      accountType : new FormControl  (''),
      status : new FormControl  (''), 
      balance : new FormControl  (''), 
      availableBalance : new FormControl  (''),  
      exemptGMF : new FormControl  (''),
      idNumber : new FormControl ('')
      
     
  });
    this.formData = this.formDataService.getFormData();
    // let id = this.formData.value.id;
    // let idNumber = this.formData.value.idNumber;
   // console.log(id,idNumber);
  }
  // customer!  : customersListI;
ngOnInit(): void {
  
}
  postForm(){
    Object.assign(this.formData1.value, this.NewAccount);
    let idNumber = this.formData.value.idNumber;
      this.api.postAccount(this.formData1.value,idNumber).subscribe(responsex =>{
          //  let response:ResponseI = responsex;
           console.log(this.formData1.value);
           console.log(responsex);
           console.log(responsex.body);
        
          if(responsex.statusText == 'OK'){ 
            this.alerts.showSuccess('Account Created','Done');
            this.router.navigate(['dashboard']);
          }else{
            this.alerts.showError('Account Not Created','Error');
          }
      })
      this.formDataService.setFormData1(this.formData1);
  }
  exit(){
    this.router.navigate(['dashboard']);
  }
}


