import { Injectable } from '@angular/core';
import Swal, { SweetAlertIcon } from 'sweetalert2';


@Injectable({
  providedIn: 'root'
})
export class AlertsService {

  constructor () { }

  alert(title: string, icon?: SweetAlertIcon, text?: string, confirmButton?: boolean) {
    return Swal.fire({
      position: "top-end",
      title: title,
      text: text,
      icon: icon || "success",
      timer: 5000,
      showConfirmButton: confirmButton || false,
      showCloseButton: true,
      timerProgressBar: true,
    })
  }
}
