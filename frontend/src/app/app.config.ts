import { registerLocaleData } from '@angular/common';
import localePtBr from '@angular/common/locales/pt';
import { LOCALE_ID } from '@angular/core';

import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { provideAnimations } from '@angular/platform-browser/animations';
import { routes } from './app.routes';

import { HttpClientModule } from '@angular/common/http';
import { importProvidersFrom } from '@angular/core';

import { provideToastr } from 'ngx-toastr';

registerLocaleData(localePtBr);
export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes), provideAnimations(), importProvidersFrom(HttpClientModule),
  provideToastr({
    newestOnTop: true,
    closeButton: true,
    progressBar: true,
    progressAnimation: 'decreasing',
    timeOut: 5000,
    positionClass: 'toast-top-right',
    preventDuplicates: true,
  }), { provide: LOCALE_ID, useValue: "pt-BR" }]
};

