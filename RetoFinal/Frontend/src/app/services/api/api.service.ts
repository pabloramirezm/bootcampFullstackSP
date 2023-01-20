import { Injectable } from '@angular/core';

import { ResponseI } from '../../models/response.interface';
import { customersListI } from '../../models/customersList.interface';
import { HttpClient, HttpHeaders, HttpStatusCode,HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { customerEditI } from '../../models/customeredit.interface';
import { ProductsI } from '../../models/products.interface';
import { ProductsListI } from 'src/app/models/productsList.interface';
import { TransactionI } from 'src/app/models/transactions.interface';


@Injectable({
  providedIn: 'root'
})
export class ApiService {
  


  url: string = "http://localhost:8082/";
  // idNumber:string |any;
  constructor(private http:HttpClient ) { }

  
  getAllCustomers():Observable<customersListI[]>{
    let direccion = this.url + "customer" ;
    return this.http.get<customersListI[]>(direccion);
  }
 
  getOneCustomer(idNumber:any):Observable<customerEditI>{
    let direccion = this.url + "customer?id=" + idNumber;
    return this.http.get<customerEditI>(direccion);
      
  }  
  putCustomer(form:customerEditI){
    let direccion = this.url + "customer";
    return this.http.put<HttpResponse<any>>(direccion, form,{observe: "response"});
   
  }
   deleteCustomer(idNumber:any){
    
    let direccion = this.url + "customer?id=" + idNumber;
    return this.http.delete(direccion,{responseType: "text"});
    }
  postCustomer(body:any,idNumber:String){
    let direccion = this.url  + "customer?id=" + idNumber ;
  
    return this.http.post<HttpResponse<any>>(direccion,body,{observe: "response"});
    } 
   //---------------------------------------------------------------------------------- 
   getAllAccounts(idNumber:String){
    let direccion = this.url + "product?customerId=" + idNumber ;
    return this.http.get<ProductsListI[]>(direccion);
   }
  postAccount(body:any,idNumber:String){
    let direccion = this.url  + "product?id="+ idNumber;    
    return this.http.post<HttpResponse<any>>(direccion,body,{observe: "response"});
  }
  putCancelAccount(body:any,accountNumber:String){
      let direccion = this.url  + "product/cancel?id=" + accountNumber;
      return this.http.put<HttpResponse<any>>(direccion,body,{observe: "response"});
  }
  putActivateAccount(body:any,accountNumber:String){
    let direccion = this.url  + "product/active?id="+ accountNumber ;
    return this.http.put<HttpResponse<any>>(direccion,body,{observe: "response"});
}  
  getAccount(idNumber:String):Observable<ProductsI>{
    let direccion = this.url  + "product?customerId=" + idNumber;
    return this.http.get<ProductsI>(direccion);
} 
  deleteAccount(accountNumber:String){    
    let direccion = this.url + "customer?id=" + accountNumber;
    return this.http.delete<HttpResponse<any>>(direccion,{observe: "response"});
}
//---------------------------------------------------------------------------------------  
  postTransaction(body:any,accountNumber2:String){
    let direccion = this.url  + "transaction?id=" + accountNumber2;
    return this.http.post(direccion,body,{responseType: "text"});
}   
  postTransfers(body:any,accountNumber1:String,accountNumber2:String){
    let direccion = this.url  + "transaction?destinationAccount=" + accountNumber1 + "&rootAccount="+ accountNumber2;
    return this.http.post(direccion,body,{responseType: "text"});
}
  getTransaction(accountNumber:String){
    let direccion = this.url + "transaction?id=" + accountNumber ;
    return this.http.get<TransactionI[]>(direccion);
}
}