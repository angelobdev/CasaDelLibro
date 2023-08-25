import {Utente} from "./utente.model";

export interface Carrello{
  id: number,
  dataCreazione: Date,
  importo: number,
  acquistato: boolean,
  utente: Utente,
}
