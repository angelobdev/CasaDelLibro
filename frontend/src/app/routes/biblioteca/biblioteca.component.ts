import {Component, OnInit} from '@angular/core';
import {LibroService} from "../../services/libro.service";
import {Carrello} from "../../models/carrello.model";
import {Libro} from "../../models/libro.model";
import {CarrelloService} from "../../services/carrello.service";
import {faArrowDownWideShort, faBars, faClose, faSearch} from '@fortawesome/free-solid-svg-icons';
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-biblioteca',
  templateUrl: './biblioteca.component.html',
  styleUrls: ['./biblioteca.component.scss']
})
export class BibliotecaComponent implements OnInit {

  isLoggedIn = false;
  listaLibri: Array<Libro> = [];
  carrello: Carrello | null = null;

  // Icone
  faSearch = faSearch;
  faSort = faArrowDownWideShort;

  // Sort
  asc = false;

  // Ricerca
  ricerca: string = "";

  constructor(private libroService: LibroService, private carrelloService: CarrelloService, private authService: AuthService) {
  }

  ngOnInit(): void {

    this.isLoggedIn = this.authService.isLoggedIn();

    this.libroService.getAll().subscribe({
      next: data => {
        this.listaLibri = data as Array<Libro>;
      },
      error: err => {
        console.log(err);
      }
    });

    if (this.authService.isLoggedIn()) {
      this.carrelloService.getUpdate().subscribe({
        next: data => {
          this.carrello = data;
        },
        error: err => {
          console.log(err);
        }
      });
    }
  }

  alertNotLogged() {
    alert("Non sei loggato!");
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

  protected readonly alert = alert;
  protected readonly faBars = faBars;
  protected readonly faClose = faClose;
}
