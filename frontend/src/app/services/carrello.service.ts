import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {StorageService} from "./storage.service";
import {BehaviorSubject, Observable} from "rxjs";
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
        let utenteID = storageService.getJWToken()!.id;

        // Cerco un carrello già esistente
        this.http.get(this.URL + "/get/" + utenteID, this.httpOptions).subscribe({
                next: data => {

                    if (data) {
                        this.sendUpdate(data as Carrello);
                    } else {

                        // Altrimenti ne creo uno nuovo
                        this.http.post(this.URL + "/crea/" + utenteID, {}, this.httpOptions).subscribe({
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

    // OBSERVER

    sendUpdate(carrello: Carrello) {
        this.carrello.next(carrello);
    }

    getUpdate(): Observable<Carrello | null> {
        return this.carrello.asObservable();
    }

    // METODI

    aggiungiLibro(libroID: number) {
        this.http.post(this.URL + "/aggiungi/" + this.carrello.getValue()?.id + "/" + libroID, {}, this.httpOptions).subscribe({
            next: data => {
                this.sendUpdate(data as Carrello);
            },
            error: err => {
                console.log(err);
            }
        });
    }

    rimuoviLibro(libroID: number) {
        this.http.post(this.URL + "/rimuovi/" + this.carrello.getValue()?.id + "/" + libroID, {}, this.httpOptions).subscribe({
            next: data => {
                this.sendUpdate(data as Carrello);
            },
            error: err => {
                console.log(err);
            }
        });
    }

    svuotaCarrello() {
        this.http.post(this.URL + "/svuota/" + this.carrello.getValue()?.id, {}, this.httpOptions).subscribe({
            next: data => {
                this.sendUpdate(data as Carrello);
            },
            error: err => {
                console.log(err);
            }
        })
    }

}
