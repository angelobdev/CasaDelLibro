import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Ordine} from "../models/ordine.model";

@Injectable({
  providedIn: 'root'
})
export class OrdineService {

  URL = "http://localhost:8080/api/ordine";

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) {
  }

  create(carrelloID: number, spedizioneID: number, ritiroID: number) {
    return this.http.post<Ordine>(this.URL + "/create",
      {carrelloID: carrelloID, spedizioneID: spedizioneID, ritiroID: ritiroID},
      this.httpOptions
    );
  }

  getById(ordineID: number) {
    return this.http.get<Ordine>(this.URL + "/get/" + ordineID);
  }

  getAll() {
    return this.http.get<Array<Ordine>>(this.URL + "/get/all");
  }

}
