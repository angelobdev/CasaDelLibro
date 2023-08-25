import {Carrello} from "./carrello.model";
import {Ritiro} from "./ritiro.model";
import {Spedizione} from "./spedizione.model";

export interface Ordine {
  id: number,
  data: Date,
  importo: number,
  carrello: Carrello | null,
  spedizione: Spedizione | null,
  ritiro: Ritiro | null,
}
