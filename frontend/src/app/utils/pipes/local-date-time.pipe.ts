import { Pipe, PipeTransform } from '@angular/core';

type DateFormats = "dd/MM/yyyy" | "dd/MM/yyyy HH:mm" | "dd/MM/yyyy hh:mm" | "dd/MM/yyyy HH:mm:ss" | "dd/MM/yyyy hh:mm:ss" | "dd/MM/yyyy HH:mm:ss.S" | "dd/MM/yyyy hh:mm:ss.S"

@Pipe({
  name: 'localDateTime',
  standalone: true
})
export class LocalDateTimePipe implements PipeTransform {

  transform(value?: Array<number>, ...args: DateFormats[]): unknown {
    if (value === undefined) return

    const [ano, mes, dia, hora, minuto, segundo, millis] = value
    const [format] = args

    if (format === "dd/MM/yyyy") {
      return `${dia}/${mes}/${ano}`
    }

    if (format === "dd/MM/yyyy HH:mm") {
      return `${dia}/${mes}/${ano} ${hora}:${minuto}`
    }

    if (format === "dd/MM/yyyy hh:mm") {
      return `${dia}/${mes}/${ano} ${hora}:${this.validMinusTenNumber(minuto)}`
    }

    if (format === "dd/MM/yyyy HH:mm:ss") {
      return `${dia}/${mes}/${ano} ${hora}:${this.validMinusTenNumber(minuto)}:${this.validMinusTenNumber(segundo)}`
    }

    if (format === "dd/MM/yyyy hh:mm:ss") {
      return `${dia}/${mes}/${ano} ${hora}:${this.validMinusTenNumber(minuto)}:${this.validMinusTenNumber(segundo)}`
    }

    if (format === "dd/MM/yyyy HH:mm:ss.S") {
      return `${dia}/${mes}/${ano} ${hora}:${this.validMinusTenNumber(minuto)}:${this.validMinusTenNumber(segundo)}.${millis}`
    }

    if (format === "dd/MM/yyyy hh:mm:ss.S") {
      return `${dia}/${mes}/${ano} ${hora}:${this.validMinusTenNumber(minuto)}:${this.validMinusTenNumber(segundo)}.${millis}`
    }

    return value;
  }

  validMinusTenNumber(value: number) {
    if (value >= 10) return value
    return `0${value}`
  }

}
