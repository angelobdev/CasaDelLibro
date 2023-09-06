import {Libro} from "./libro.model";

export interface CarrelloLibro {
  id: number,
  // carrello: Carrello, (@JsonIgnore)
  libro: Libro,
  quantita: number
}
