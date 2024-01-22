import { Contato } from "./contato";

export interface Transferencia {
  pessoaEnvio: string,
  contaDestino: number,
  contaEnvio: number | string,
  contatoNome: string,
  dataTransferencia: string,
  valor: number
}
