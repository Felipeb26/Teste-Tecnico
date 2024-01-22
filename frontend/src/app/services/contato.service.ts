import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Contato } from "../interfaces/contato";


@Injectable({
  providedIn: "root"
})
export class ContatoService {

  private readonly apiUrl = "http://localhost:8080/v1/contato"

  constructor (private http: HttpClient) { }


  findAll(): Observable<Contato[]> {
    return this.http.get<Contato[]>(this.apiUrl)
  }

  findById(id: number): Observable<Contato> {
    return this.http.get<Contato>(`${this.apiUrl}/${id}`)
  }

  save(contato: Contato): Observable<Contato> {
    return this.http.post<Contato>(this.apiUrl, contato);
  }

  delete(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`)
  }

}
