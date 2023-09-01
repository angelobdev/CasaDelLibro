import {Utente} from "./utente.model";
import {CarrelloLibro} from "./carrello-libro.model";

export interface Carrello {
  id: number,
  dataCreazione: Date,
  importo: number,
  acquistato: boolean,
  libri: Array<CarrelloLibro>,
  utente: Utente,
}
