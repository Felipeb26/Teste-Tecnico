import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

type Alerts = "info" | "error" | " warning" | "sucess"

@Injectable({
  providedIn: 'root'
})
export class AlertsService {

  constructor (private toastr: ToastrService) { }

  alert(title: string, text?: string, icon?: Alerts) {
    switch (icon) {
      case "info":
        return this.toastr.info(text, title)
      case "error":
        return this.toastr.error(text, title)
      case " warning":
        return this.toastr.warning(text, title)
      case "sucess":
        return this.toastr.success(text, title)
      default:
        return this.toastr.success(text, title)
    }
  }
}
