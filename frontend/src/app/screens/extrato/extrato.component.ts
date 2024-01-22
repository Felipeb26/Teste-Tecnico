import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Router } from '@angular/router';
import { AlertsService } from './../../services/alerts.service';
import { ChecksService } from './../../utils/checks.service';
import { TransferenciaService } from '../../services/transferencia.service';
import { Transferencia } from '../../interfaces/transferencia';

@Component({
  selector: 'app-extrato',
  standalone: true,
  templateUrl: './extrato.component.html',
  styleUrl: './extrato.component.scss',
  imports: [CommonModule,MatCardModule],
})
export class ExtratoComponent implements OnInit {

  transferencias:Transferencia[] |undefined

  constructor (private check: ChecksService, private route: Router, private alert: AlertsService, private request: TransferenciaService) { }

  ngOnInit(): void {
    const logged = this.check.isLogged()
    if (!logged) {
      const alert = this.alert.alert("usuario não encontrado", "error", "redirecionando para login")
      alert.then(() => setTimeout(() => this.route.navigate([""]), 500))
    }
    this.request.findAll().subscribe((data) => this.transferencias = data)
  }
}
