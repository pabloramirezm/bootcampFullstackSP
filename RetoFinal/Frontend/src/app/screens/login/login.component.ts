import { Component, OnInit } from '@angular/core';
import {AuthService} from '@auth0/auth0-angular';
import { Router } from '@angular/router';
import { } from '../../guards/auth.guard';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(public auth: AuthService, private router:Router) { }
 
  Login(){
    this.auth.loginWithRedirect();
    
    }
  //    if(this.auth.isAuthenticated$){
      
  //    }else{
  //     this.router.navigate(['dashboard']);
  //    }
    
  // }
  

  // loginForm! : FormGroup;
//     usuario : new FormControl('',Validators.required),
//     password : new FormControl('',Validators.required)
//  })
  //  errorStatus : boolean = false;
  //  errorMsj: any = '' ;
  
  //  ngOnInit(): void {
  //    this.loginForm = this.fb.group({
  //     user : ['',[Validators.required,]],
  //  password : new FormControl('',Validators.required)
  //    })
  //   }

    // checkLocalStorage(){
      
    //   if(this.auth.isAuthenticated$){
    //     this.router.navigate(['dashboard']);
    //   }
    // }

    // onLogin(form:any){
      // this.api.LoginBy(form).subscribe((data:any)=>{
      //   let dataResponse:ResponseI = data ;
      //    if (dataResponse.status_code == "ok"){
      //     localStorage.setItem("token",dataResponse.result.token);
      //     this.router.navigate(['dashboard']);
      //    }else {
      //     this.errorStatus = true;
      //     this.errorMsj = dataResponse.result.error_msg;
          
      //    }
      // })
    
// }
}
