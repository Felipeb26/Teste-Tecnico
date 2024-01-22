import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { AlertsService } from '../../services/alerts.service';
import { ChecksService } from './../../utils/checks.service';


@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule, RouterOutlet, MatButtonModule, MatButtonModule, MatToolbarModule, MatMenuModule, MatSidenavModule, MatListModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit {

  user: string | null
  opened: boolean = true
  conta: string | null

  constructor (private check: ChecksService, private route: Router, private alert: AlertsService) {
    this.user = sessionStorage.getItem("login")
    this.conta = sessionStorage.getItem("conta")
  }


  ngOnInit(): void {
    const logged = this.check.isLogged()
    if (!logged) {
      setTimeout(() => this.route.navigate([""]), 500)
    }
    setInterval(() => {
      this.showUserData()
    }, 5000);
  }

  showUserData() {
    this.user = sessionStorage.getItem("login")
    this.conta = sessionStorage.getItem("conta")
  }

}
