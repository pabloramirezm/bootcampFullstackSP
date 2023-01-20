import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Injectable({
providedIn: 'root'
})
export class FormDataService {
formData!: FormGroup;
formData1!: FormGroup;
formData2!: FormGroup;

setFormData(data: FormGroup) {
this.formData = data;
}
setFormData1(data1: FormGroup) {
  this.formData1 = data1;
  }
  setFormData2(data2: FormGroup) {
    this.formData2 = data2;
  }
 getFormData() {
return this.formData;
}
getFormData1() {
  return this.formData1;
  }
  getFormData2() {
    return this.formData2;
    }
}