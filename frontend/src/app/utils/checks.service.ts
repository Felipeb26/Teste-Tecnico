import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ChecksService {

  constructor () { }

  isAnythingNull(args: any[]) {
    for (let arg of args) {
      if (arg === undefined || null)
        return true
    }
    return false
  }

  checkTaxa(dias: number) {
    if (dias < 1) {
      return 2.5
    }
    if (dias >= 1 && dias <= 10) {
      return 0
    }
    if (dias >= 11 && dias <= 20) {
      return 8.2
    }
    if (dias >= 21 && dias <= 30) {
      return 6.9
    }
    if (dias >= 31 && dias <= 40) {
      return 4.7
    }
    if (dias >= 41 && dias <= 50) {
      return 1.7
    }
    return undefined
  }

  calcularTaxa(valor: number, taxa: number) {
    return valor + ((taxa / 100) * valor)
  }


  isLogged(): boolean {
    const is = sessionStorage.getItem("login")
    return is !== null
  }
}
