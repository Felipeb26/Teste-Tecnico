import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef, MatDialogTitle } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { NgxCurrencyDirective } from 'ngx-currency';
import { ContatoService } from '../../../services/contato.service';

@Component({
  selector: 'app-contato.create',
  standalone: true,
  templateUrl: './contato.create.component.html',
  styleUrl: './contato.create.component.scss',
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatDialogClose,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonModule, FormsModule, DatePipe, CurrencyPipe,
    FormsModule, ReactiveFormsModule, NgxCurrencyDirective
  ],
})
export class ContatoCreateComponent {


  constructor (
    public dialogRef: MatDialogRef<ContatoCreateComponent>,
    private request: ContatoService
  ) { }

  onNoClick(): void {
    this.dialogRef.close();
  }

  applyForm = new FormGroup({
    nome: new FormControl("", Validators.required),
    conta: new FormControl("", [Validators.required, Validators.minLength(5), Validators.maxLength(10)]),
    banco: new FormControl("", Validators.required),
    agencia: new FormControl("", [Validators.required, Validators.min(2), Validators.max(2)]),
  });

  save(contato: FormGroup) {
    this.request.save(contato.value).subscribe(
      (data) => console.log(data.nome),
      (error) => console.log(error)
    )
  }
}
