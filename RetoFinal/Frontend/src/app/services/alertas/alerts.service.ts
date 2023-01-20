import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class AlertsService {

  constructor(private toast: ToastrService) { }

  showSuccess(text: string | any, title: string | any){
   this.toast.success(text,title);
  }

  showError(text: string | any, title: string | any){
    this.toast.error(text,title);
  }

}

