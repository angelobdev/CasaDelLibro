import {Libro} from "./libro.model";
import {Ruolo} from "./ruolo.model";

export interface Utente {
  id: number,
  nome: string,
  cognome: string,
  dataNascita: Date,
  username: string,
  email: string,
  password: string,
  avatar: string,
  ruolo: Ruolo,
  libriPreferiti: Array<Libro>
}
