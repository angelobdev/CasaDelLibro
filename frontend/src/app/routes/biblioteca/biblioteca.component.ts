import {Component, OnInit} from '@angular/core';
import {LibroService} from "../../services/libro.service";
import {Carrello} from "../../models/carrello.model";
import {Libro} from "../../models/libro.model";
import {CarrelloService} from "../../services/carrello.service";
import {AuthService} from "../../services/auth.service";
import * as far from "@fortawesome/free-regular-svg-icons";
import * as fas from "@fortawesome/free-solid-svg-icons";
import {UtenteService} from "../../services/utente.service";
import {StorageService} from "../../services/storage.service";

@Component({
  selector: 'app-biblioteca',
  templateUrl: './biblioteca.component.html',
  styleUrls: ['./biblioteca.component.scss']
})
export class BibliotecaComponent implements OnInit {

  dataLoaded = false;
  isLoggedIn = false;
  listaLibri: Array<Libro> = [];
  libriPreferiti: Array<Libro> = [];
  carrello: Carrello | null = null;

  // Icone
  faSearch = fas.faSearch;
  faSort = fas.faArrowDownWideShort;
  faLikeEmpty = far.faHeart;
  faLikeFull = fas.faHeart;

  // Sort
  asc = false;

  // Ricerca
  ricerca: string = "";

  constructor(private storageService: StorageService, private libroService: LibroService, private carrelloService: CarrelloService, private authService: AuthService, private utenteService: UtenteService) {
  }

  ngOnInit(): void {

    this.isLoggedIn = this.authService.isLoggedIn();

    this.libroService.getAll().subscribe({
      next: data => {
        this.listaLibri = data as Array<Libro>;
        this.dataLoaded = true;
      },
      error: err => {
        console.log(err);
      }
    });

    if (this.authService.isLoggedIn()) {

      let utenteID = this.storageService.getJWToken()!.id;

      // Carico il carrello
      this.carrelloService.getUpdate().subscribe({
        next: data => {
          this.carrello = data;
        },
        error: err => {
          console.log(err);
        }
      });

      // Carico i libri preferiti
      this.utenteService.getLibriPreferiti(utenteID).subscribe({
        next: libri => {
          this.libriPreferiti = libri;
        },
        error: err => {
          console.log(err);
        }
      });

    }


  }

  alertNotLogged() {
    alert("Esegui il login prima!");
  }

  ampliaDescrizione(id: number) {
    let descrizione = document.getElementById("descrizione-" + id);
    let button = document.getElementById("show-" + id);

    if (descrizione!.style.webkitLineClamp !== "999") {
      descrizione!.style.webkitLineClamp = "999";
      button!.innerHTML = "Mostra meno"
    } else {
      descrizione!.style.webkitLineClamp = "10";
      button!.innerHTML = "Mostra di piu..."
    }
  }

  aggiungiLibroAlCarrello(libroID: number) {
    this.carrelloService.aggiungiLibro(libroID, 1);
  }

  // Sorting

  cercaPerTitolo() {

    this.libroService.getAll().subscribe({
      next: data => {
        this.listaLibri = data as Array<Libro>;

        if (this.ricerca.length == 0) {
          return;
        }

        this.listaLibri = this.listaLibri.filter((libro) => {
          let titoloBool = libro.titolo.toLowerCase().includes(this.ricerca.toLowerCase());
          let autoreBool = libro.autore.toLowerCase().includes(this.ricerca.toLowerCase());
          let descrizioneBool = libro.descrizione.toLowerCase().includes(this.ricerca.toLowerCase());
          return titoloBool || autoreBool || descrizioneBool;
        });

      },
      error: err => {
        console.log(err);
      }
    });

  }

  toggleLike(libroID: number) {

    let utenteID = this.storageService.getJWToken()!.id;

    this.utenteService.toggleLike(utenteID, libroID).subscribe({
      next: isLike => {
        console.log(isLike ? "Il libro ti piace" : "Il libro non ti piace");

        // Carico i libri preferiti
        this.utenteService.getLibriPreferiti(utenteID).subscribe({
          next: libri => {
            this.libriPreferiti = libri;
          },
          error: err => {
            console.log(err);
          }
        });
      },
      error: err => {
        console.log(err);
      }
    })
  }

  isLibroPreferito(libroID: number) {
    let isPreferito = false;
    this.libriPreferiti.forEach((libro) => {
      if (libro.id == libroID)
        isPreferito = true;
    });
    return isPreferito;
  }

  sortByTitolo() {
    this.listaLibri = this.listaLibri.sort((a, b) => {
      return this.asc ? a.titolo.localeCompare(b.titolo) : b.titolo.localeCompare(a.titolo);
    });
    this.asc = !this.asc;
  }

  sortByAutore() {
    this.listaLibri = this.listaLibri.sort((a, b) => {
      let autoreA = a.autore.split(" ").at(-1);
      let autoreB = b.autore.split(" ").at(-1);
      return this.asc ? autoreA!.localeCompare(autoreB!) : autoreB!.localeCompare(autoreA!);
    });
    this.asc = !this.asc;
  }

  sortByPrezzo() {
    this.listaLibri = this.listaLibri.sort((a, b) => {
      return this.asc ? (a.prezzo - b.prezzo) : (b.prezzo - a.prezzo);
    });
    this.asc = !this.asc;
  }

  sortByQuantita() {
    this.listaLibri = this.listaLibri.sort((a, b) => {
      return this.asc ? (a.quantita - b.quantita) : (b.quantita - a.quantita);
    })
    this.asc = !this.asc;
  }
}
