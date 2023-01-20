import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {AuthService} from '@auth0/auth0-angular';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  constructor (public auth: AuthService, private router:Router){}

  Logout(){
    this.auth.logout();
    this.router.navigate(['login']);
  }

  // Custgo(){
  //   this.router.navigate(['dasnboard']);
  // }
  Trango(){
    this.router.navigate(['transaction']);
   }
  Dashgo(){
    this.router.navigate(['dashboard']);
  }
  }


