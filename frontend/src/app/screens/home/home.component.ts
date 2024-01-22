import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
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

@Component({
  selector: 'app-home',
  standalone: true,
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
  imports: [CommonModule, MatFormFieldModule, MatInputModule, MatSelectModule, MatCardModule,
    MatDatepickerModule, MatNativeDateModule, MatButtonModule, FormsModule, DatePipe, CurrencyPipe,
    FormsModule, ReactiveFormsModule, NgxCurrencyDirective]
})
export class HomeComponent implements OnInit {

  visible: boolean = false

  applyForm = new FormGroup({
    username: new FormControl("", Validators.required),
    senha: new FormControl("", Validators.required)
  });

  constructor (
    private route:Router) { }

  ngOnInit(): void {

  }

  salvarLogin(login:FormGroup){
    if(login.invalid) return;
    this.route.navigate(["/extrato"])
    sessionStorage.setItem("login", login.value.username)
    sessionStorage.setItem("conta", "0173736-8")
  }
}
