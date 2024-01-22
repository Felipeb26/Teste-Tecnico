import { Routes } from '@angular/router';
import { HomeComponent } from './screens/home/home.component';
import { ExtratoComponent } from './screens/extrato/extrato.component';
import { ContatosComponent } from './screens/contatos/contatos.component';
import { TransacaoComponent } from './screens/transacao/transacao.component';

export const routes: Routes = [
  { path: "*", redirectTo: "" },
  { path: "", component: HomeComponent, title: "home" },
  { path: "extrato", component: ExtratoComponent, title: "extrato" },
  { path: "contatos", component: ContatosComponent, title: "contatos" },
  { path: "transacao", component: TransacaoComponent, title: "transferencia" }
];
