import { Component, OnInit } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Router } from '@angular/router';
import { AlertsService } from './../../services/alerts.service';
import { ChecksService } from './../../utils/checks.service';

@Component({
  selector: 'app-extrato',
  standalone: true,
  templateUrl: './extrato.component.html',
  styleUrl: './extrato.component.scss',
  imports: [MatCardModule],
})
export class ExtratoComponent implements OnInit {

  constructor (private check: ChecksService, private route: Router, private alert: AlertsService) { }

  ngOnInit(): void {
    const logged = this.check.isLogged()
    if (!logged) {
      const alert = this.alert.alert("usuario não encontrado", "error", "redirecionando para login")
      alert.then(() => setTimeout(() => this.route.navigate([""]), 500))
    }
  }
}
