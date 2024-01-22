import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Contato } from "../interfaces/contato";
import { Transferencia } from "../interfaces/transferencia";


@Injectable({
  providedIn: 'root'
})
export class TransferenciaService {
  private readonly apiUrl = "http://localhost:8080/v1/contato"

  constructor (private http: HttpClient) { }

  saveTransferencia(transferencia: Transferencia): Observable<Transferencia> {
    return this.http.post<Transferencia>(this.apiUrl, transferencia)
  }
}
