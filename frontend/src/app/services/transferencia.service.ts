import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Transferencia } from "../interfaces/transferencia";


@Injectable({
  providedIn: 'root'
})
export class TransferenciaService {
  private readonly apiUrl = "http://localhost:8080/v1/transferencia"

  constructor (private http: HttpClient) { }

  saveTransferencia(transferencia: Transferencia): Observable<Transferencia> {
    return this.http.post<Transferencia>(this.apiUrl, transferencia)
  }

  findAll():Observable<Transferencia[]>{
    return this.http.get<Transferencia[]>(this.apiUrl)
  }
}
