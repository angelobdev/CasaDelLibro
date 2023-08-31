import {Utente} from "./utente.model";
import {Libro} from "./libro.model";

export interface Carrello {
  id: number,
  dataCreazione: Date,
  importo: number,
  acquistato: boolean,
  libri: Array<Libro>,
  utente: Utente,
}
