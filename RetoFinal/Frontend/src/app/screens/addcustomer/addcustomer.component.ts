import { Component, OnInit,Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators} from '@angular/forms';
import { customerEditI } from '../../models/customeredit.interface';
import { ResponseI } from '../../models/response.interface';
import { ApiService } from '../../services/api/api.service';
import { AlertsService } from '../../services/alertas/alerts.service';
import { Router, ActivatedRoute } from '@angular/router';
import { from } from 'rxjs';
import { EditcustomerComponent } from '../editcustomer/editcustomer.component';
import { HttpStatusCode } from '@angular/common/http';

@Component({
  selector: 'app-addcustomer',
  templateUrl: './addcustomer.component.html',
  styleUrls: ['./addcustomer.component.css']
})
export class AddcustomerComponent implements OnInit {
  newForm: FormGroup;
  newCustomer! : customerEditI;
    // formData = new FormGroup({
    // name :  new FormControl ('')  ,
    // lastName : new FormControl(''),
    // idNumber:  new FormControl(''),
    // email :   new FormControl(''),
    // dateOfBirth : new FormControl(''),
    // typeId:   new FormControl(''),
    

// });


  constructor(private api:ApiService, private router:Router, private alerts:AlertsService,
   ) { 
     this.newForm = new FormGroup({
      name :  new FormControl ('')  ,
      lastName : new FormControl(''),
      idNumber:  new FormControl(''),
      email :   new FormControl(''),
      dateOfBirth : new FormControl(''),
      typeId:   new FormControl(''),
    });
    
  }
  ngOnInit(): void {
    
  }
  
  postForm(){
    Object.assign(this.newForm.value, this.newCustomer);
    let idNumber = this.newForm.value.idNumber;
      this.api.postCustomer(this.newForm.value,idNumber).subscribe(responsex =>{
          //  let response:ResponseI = responsex;
           console.log(responsex.status);
           console.log(responsex);
           console.log(responsex.body);
        
          if(responsex.status == 200){ 
            this.alerts.showSuccess('Customer Created','Done');
            this.router.navigate(['dashboard']);
          }else{
            this.alerts.showError('Customer Not Created','Error');
          }
      })
  }

  exit(){
    this.router.navigate(['dashboard']);
  }

}