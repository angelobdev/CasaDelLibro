import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {MessageResponse} from "../payload/response/message.response";
import {Utente} from "../models/utente.model";
import {Libro} from "../models/libro.model";

@Injectable({
  providedIn: 'root'
})
export class UtenteService {

  URL = "http://localhost:8080/api/utente";

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) {
  }

  get(utenteID: number) {
    return this.http.get<Utente>(this.URL + "/get/" + utenteID);
  }

  getLibriPreferiti(utenteID: number) {
    return this.http.get<Array<Libro>>(this.URL + "/get/libri/" + utenteID);
  }

  update(utenteID: number, utente: Utente) {
    return this.http.post<Utente>(this.URL + "/update/" + utenteID, utente, this.httpOptions);
  }

  toggleLike(utenteID: number, libroID: number) {
    return this.http.post<boolean>(this.URL + "/toggleLike/" + utenteID + "/" + libroID, {});
  }

  getAvatar(username: string) {
    return this.http.get<MessageResponse>(this.URL + "/get/avatar/" + username);
  }
}
