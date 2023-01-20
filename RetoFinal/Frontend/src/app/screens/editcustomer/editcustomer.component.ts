import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { customerEditI } from '../../models/customeredit.interface';
import { ResponseI } from '../../models/response.interface';
import { ApiService } from '../../services/api/api.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { AlertsService } from '../../services/alertas/alerts.service';
import {FormDataService } from '../../services/formData/form-data.service';

 
@Component({
  selector: 'app-editcustomer',
  templateUrl: './editcustomer.component.html',
  styleUrls: ['./editcustomer.component.css']
})
export class EditcustomerComponent implements OnInit {
  formData: FormGroup; 
  constructor(private activerouter:ActivatedRoute , private router:Router , 
    private api:ApiService, private alerts:AlertsService,private formDataService: FormDataService){
      this.formData = new FormGroup({
        name :  new FormControl ('',[Validators.required])  ,
        lastName : new FormControl('',[Validators.required]),
        idNumber:  new FormControl('',[Validators.required]),
        email :   new FormControl('bad@', [Validators.email]),
        dateOfBirth : new FormControl('',[Validators.required]),
        typeId:   new FormControl('',[Validators.required]),
        id: new FormControl('')
        // token: new FormControl('',[Validators.required])
  
    });  
    }

         
   dateCustomer !: customerEditI;

    // editForm = new FormGroup({
    // name :  new FormControl ('')  ,
    // lastName : new FormControl(''),
    // idNumber:  new FormControl(''),
    // email :   new FormControl(''),
    // dateOfBirth : new FormControl(''),
    // typeId:   new FormControl(''),
    // token: new FormControl('')

    

 ngOnInit(): void{
  let customerid = this.activerouter.snapshot.paramMap.get('idNumber');
  // let token = this.getToken();
  this.api.getOneCustomer(customerid).subscribe(data=>{

    this.dateCustomer = data;
    console.log(data);
      this.formData.setValue({
      'name': this.dateCustomer.name,
      'lastName': this.dateCustomer.lastName,
      'idNumber': this.dateCustomer.idNumber,
      'email': this.dateCustomer.email,
      'dateOfBirth': this.dateCustomer.dateOfBirth,
      'typeId': this.dateCustomer.typeId,
       'id': this.dateCustomer.id
      // 'token' : token
    });
    // console.log(this.editForm.value);
  })
   }  

  getToken(){
    // return localStorage.getItem('token');
  }

  postForm(){

    //this.dateCustomer.name = String(this.editForm.value.name);
    Object.assign(this.dateCustomer, this.formData.value);
    // console.log(this.dateCustomer);
    
    this.api.putCustomer(this.dateCustomer).subscribe(data =>{
      // let response:ResponseI = data;
      console.log(this.dateCustomer);
      console.log(this.formData.value);
     
      if(data.statusText == "OK"){ 
        this.alerts.showSuccess('Dates modificaded','Done');
        this.router.navigate(['dashboard']);
      }else{
        this.alerts.showError('Customer Not Modificated','Error');
      }
  })
}
delete(){
  
  // let datos:customerEditI = this.editForm.value;
  Object.assign(this.dateCustomer, this.dateCustomer);
  this.api.deleteCustomer(this.dateCustomer.idNumber).subscribe(response =>{
        // let respuesta: ResponseI |any= data;
        // console.log(response.statusText);
        // console.log(response.status);
      if(response == 'Cliente eliminado con exito' ){
          this.alerts.showSuccess('Customer deleted','Done');
          this.router.navigate(['dashboard']);
      }else{
          this.alerts.showError('Can´t delete this customer','Error');
      }
  })
}


exit(){
  this.router.navigate(['dashboard']);
}

cAccount(){
  this.formDataService.setFormData(this.formData);
  this.router.navigate(['accounts',this.dateCustomer.idNumber]);
}
ViewAccounts(){
  this.router.navigate(['addaccount',this.dateCustomer.idNumber]);
}

}
