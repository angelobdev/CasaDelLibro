import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {StorageService} from "./storage.service";
import {BehaviorSubject} from "rxjs";
import {Carrello} from "../models/carrello.model";

@Injectable({
  providedIn: 'root'
})
export class CarrelloService {

  private carrello = new BehaviorSubject<Carrello | null>(null)

  URL = "http://localhost:8080/api/carrello";

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient, private storageService: StorageService) {

    let jwt = storageService.getJWToken();

    if (jwt) {

      let utenteID = jwt.id;

      // Cerco un carrello già esistente
      this.http.get<Carrello>(this.URL + "/get/" + utenteID, this.httpOptions).subscribe({
          next: data => {

            if (data) {
              this.sendUpdate(data as Carrello);
            } else {

              // Altrimenti ne creo uno nuovo
              this.http.post<Carrello>(this.URL + "/create/" + utenteID, {}, this.httpOptions).subscribe({
                next: data => {
                  this.sendUpdate(data as Carrello);
                },
                error: err => {
                  console.log(err);
                }
              });

            }

          },
          error: err => {
            console.log(err)
          }
        }
      );

    }
  }

  // OBSERVER

  sendUpdate(carrello: Carrello) {
    this.carrello.next(carrello);
  }

  getUpdate() {
    return this.carrello.asObservable();
  }

  // METODI

  aggiungiLibro(libroID: number, quantita: number) {
    this.http.post<Carrello>(this.URL + "/aggiungi/" + this.carrello.getValue()?.id + "/" + libroID + "/" + quantita, {}, this.httpOptions).subscribe({
      next: data => {
        this.sendUpdate(data);
      },
      error: err => {
        console.log(err);
      }
    });
  }

  rimuoviLibro(libroID: number, quantita: number) {
    this.http.post<Carrello>(this.URL + "/rimuovi/" + this.carrello.getValue()?.id + "/" + libroID + "/" + quantita, {}, this.httpOptions).subscribe({
      next: data => {
        this.sendUpdate(data);
      },
      error: err => {
        console.log(err);
      }
    });
  }

  svuotaCarrello() {
    this.http.post<Carrello>(this.URL + "/svuota/" + this.carrello.getValue()?.id, {}, this.httpOptions).subscribe({
      next: data => {
        this.sendUpdate(data);
      },
      error: err => {
        console.log(err);
      }
    })
  }

}
