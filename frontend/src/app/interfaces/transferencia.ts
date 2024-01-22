import { Contato } from "./contato";

export interface Transferencia {
  pessoaEnvio: string,
  contaDestino: Contato,
  contaEnvio: number | string,
  contatoNome: string,
  dataTransferencia: string,
  valor: number,
  dataAgendamento?: Array<number>
}
