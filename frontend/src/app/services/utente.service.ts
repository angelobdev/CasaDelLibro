import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MessageResponse} from "../payload/response/message.response";

@Injectable({
  providedIn: 'root'
})
export class UtenteService {

  URL = "http://localhost:8080/api/utente"

  constructor(private http: HttpClient) {
  }

  getAvatar(username: string) {
    return this.http.get<MessageResponse>(this.URL + "/get/avatar/" + username);
  }
}
