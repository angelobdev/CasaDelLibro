import {Component, OnInit} from '@angular/core';
import {CarrelloService} from "../../services/carrello.service";
import {StorageService} from "../../services/storage.service";
import {Carrello} from "../../models/carrello.model";
import {faTrash} from "@fortawesome/free-solid-svg-icons";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Spedizione} from "../../models/spedizione.model";
import {Ritiro} from "../../models/ritiro.model";

@Component({
  selector: 'app-carrello',
  templateUrl: './carrello.component.html',
  styleUrls: ['./carrello.component.scss']
})
export class CarrelloComponent implements OnInit {

  utenteID = -1;
  carrello: Carrello | null = null;

  // Modalita di consegna
  modalita: any = 0;
  spedizione: string = '';
  ritiro = new Date();
  isChecked = true;
  dataMinima = new Date();
  spedizioneRitiroID = -1;

  // HTTP
  URL = "http://localhost:8080/api";
  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  // Icone
  faRemove = faTrash;

  constructor(private http: HttpClient, private carrelloService: CarrelloService, private storageService: StorageService) {

  }

  ngOnInit(): void {
    this.dataMinima.setDate(this.dataMinima.getDate() + 1);

    this.utenteID = this.storageService.getJWToken()!.id;
    this.carrelloService.getUpdate().subscribe({
      next: data => {
        this.carrello = data;
      },
      error: err => {
        console.log(err);
      }
    })
  }

  aggiungiLibro(libroID: number, quantita: number) {
    this.carrelloService.aggiungiLibro(libroID, quantita);
  }

  rimuoviLibro(libroID: number, quantita: number) {
    this.carrelloService.rimuoviLibro(libroID, quantita);
  }

  svuotaCarrello() {
    this.carrelloService.svuotaCarrello();
  }

  acquistaCarrello() {

    switch (this.modalita) {

      // SPEDIZIONE
      case 0:
        this.http.post(this.URL + "/spedizione/crea",
          {indirizzo: this.spedizione}).subscribe(
          {
            next: data => {
              this.spedizioneRitiroID = (data as Spedizione).id;
            },
            error: err => {
              console.log(err);
            }
          });
        break;

      // RITIRO
      case 1:
        this.http.post(this.URL + "/ritiro/crea",
          {dataScelta: this.ritiro.toString()}).subscribe(
          {
            next: data => {
              this.spedizioneRitiroID = (data as Ritiro).id;
            },
            error: err => {
              console.log(err);
            }
          });
        break;

      // ERRORE
      default:
        throw Error("Non è stato possibile scegliere la modalità di consegna!");

    }

    // Creo l'ordine



  }

}
