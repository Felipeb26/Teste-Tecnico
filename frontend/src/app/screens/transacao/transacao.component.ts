import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Router } from '@angular/router';
import { NgxCurrencyDirective } from 'ngx-currency';
import { Transferencia } from '../../interfaces/transferencia';
import { AlertsService } from '../../services/alerts.service';
import { ContatoService } from '../../services/contato.service';
import { ChecksService } from '../../utils/checks.service';
import { Contato } from './../../interfaces/contato';
import { TransferenciaService } from '../../services/transferencia.service';


@Component({
  selector: 'app-transacao',
  standalone: true,
  templateUrl: './transacao.component.html',
  styleUrl: './transacao.component.scss',
  imports: [CommonModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatCardModule,
    MatDatepickerModule, MatNativeDateModule, MatButtonModule, FormsModule, DatePipe, CurrencyPipe,
    FormsModule, ReactiveFormsModule, NgxCurrencyDirective],
})
export class TransacaoComponent {
  contaDestino: string | null
  minDate: Date
  resume: boolean = false
  contatos!: Contato[]
  taxaTransferencia: number = 0
  valorFinal: number = 0

  id: number = 0

  applyForm = new FormGroup({
    id: new FormControl(0),
    contato: new FormControl("", Validators.required),
    conta: new FormControl("", Validators.required),
    valor: new FormControl(0, Validators.required),
    dataTransferencia: new FormControl(new Date(), Validators.required)
  });

  constructor (private checks: ChecksService,
    private alerts: AlertsService,
    private requests: ContatoService,
    private transacaoRequest: TransferenciaService,
    private route: Router) {

    const date = new Date();
    const currentYear = date.getFullYear()
    this.minDate = new Date(currentYear, date.getMonth(), date.getDate());
    this.contaDestino = sessionStorage.getItem("conta")
  }

  ngOnInit(): void {
    const logged = this.checks.isLogged()
    if (!logged) {
      const alert = this.alerts.alert("usuario não encontrado", "redirecionando para login", "error")
      setTimeout(() => this.route.navigate([""]), 500)
    }
    this.findAll()
  }

  findAll() {
    return this.requests.findAll().subscribe(
      (data) => this.contatos = data
    )
  }

  showAll(transferencia: FormGroup) {
    const date = new Date()
    const hoje = new Date(date.getFullYear(), date.getMonth(), date.getDate())

    const timeDiff = Math.abs(hoje.getTime() - transferencia.value.dataTransferencia.getTime());
    const diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));

    const taxa = this.checks.checkTaxa(diffDays)
    if (taxa === undefined) {
      this.alerts.alert("Data invalida para transferencia", undefined, "error")
      return
    }

    this.taxaTransferencia = taxa
    this.valorFinal = this.checks.calcularTaxa(transferencia.value.valor, taxa)
    this.resume = transferencia.valid
  }

  cancelarTransacao() {
    this.resume = !this.resume
    this.applyForm.reset({ contato: " ", conta: " ", valor: 0, dataTransferencia: new Date() })
  }

  confirmarTransacao() {
    const { id, conta, contato, dataTransferencia, valor } = this.applyForm.value
    const isNull = this.checks.isAnythingNull([conta, contato, dataTransferencia, valor])

    if (isNull) {
      return;
    }

    if (conta === null || conta === undefined) return;

    let entity = this.contatos.find(it => String(it.conta + '-' + it.agencia).startsWith(conta))
    if (entity?.id === null || entity?.id === undefined) return;

    const envio: Transferencia = {
      contaDestino: entity, contaEnvio: this.contaDestino!,
      contatoNome: contato!, pessoaEnvio: sessionStorage.getItem("login")!,
      valor: valor!,
      dataTransferencia: this.checks.formatDateMonth(dataTransferencia!)
    }

    this.transacaoRequest.saveTransferencia(envio).subscribe(
      (data) => {
        this.alerts.alert("transferencia agendada")
      },
      (err) => this.alerts.alert("erro ao transferir", err.message, "error")
    )
  }


}
