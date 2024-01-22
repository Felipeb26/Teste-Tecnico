import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialog, MatDialogModule } from "@angular/material/dialog";
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ContatoCreateComponent } from '../../components/views/contato.create/contato.create.component';
import { Contato } from '../../interfaces/contato';
import { AlertsService } from '../../services/alerts.service';
import { ContatoService } from '../../services/contato.service';
import { ChecksService } from '../../utils/checks.service';

@Component({
  selector: 'app-contatos',
  standalone: true,
  templateUrl: './contatos.component.html',
  styleUrl: './contatos.component.scss',
  imports: [CommonModule, MatCardModule, MatButtonModule, MatFormFieldModule, MatExpansionModule, MatDialogModule],
})
export class ContatosComponent implements OnInit {

  contatos!: Observable<Contato[]>;

  constructor (private request: ContatoService, public dialog: MatDialog,
    private checks: ChecksService, private route: Router,
    private alerts: AlertsService) { }

  ngOnInit(): void {
    const logged = this.checks.isLogged()
    if (!logged) {
      const alert = this.alerts.alert("usuario não encontrado", "error", "redirecionando para login")
      alert.then(() => setTimeout(() => this.route.navigate([""]), 500))
    }
    this.contatos = this.findAll()
  }

  findAll() {
    return this.request.findAll()
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(ContatoCreateComponent, {
    });

    dialogRef.beforeClosed().subscribe(() => this.findAll())
    dialogRef.afterClosed().subscribe(result => this.findAll());
  }

  deletarContato(id?: number) {
    if (id == undefined) return;
    this.request.delete(id).subscribe(
      (data) => this.findAll(),
      (err) => console.log(err)
    )
  }

}
