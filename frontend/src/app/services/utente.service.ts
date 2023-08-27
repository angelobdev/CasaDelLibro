import {Injectable} from '@angular/core';
import {Utente} from "../models/utente.model";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UtenteService {

  URL = "http://localhost:8080/api/user/"

  constructor(private http: HttpClient) {
  }

  getAvatar(username: string): Observable<any> {
    return this.http.get<string>(this.URL + "avatar/" + username);
  }
}
