import {Component, OnInit} from '@angular/core';
import {LibroService} from "../../services/libro.service";
import {Carrello} from "../../models/carrello.model";
import {Libro} from "../../models/libro.model";
import {CarrelloService} from "../../services/carrello.service";
import {faArrowDownWideShort, faBars, faSearch} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-biblioteca',
  templateUrl: './biblioteca.component.html',
  styleUrls: ['./biblioteca.component.scss']
})
export class BibliotecaComponent implements OnInit {

  listaLibri: Array<Libro> = [];
  carrello: Carrello | null = null;

  // Icone
  faSearch = faSearch;
  faSort = faArrowDownWideShort;

  // Sort
  asc = false;

  // Ricerca
  titoloCercato: string = "";

  constructor(private libroService: LibroService, private carrelloService: CarrelloService) {
  }

  ngOnInit(): void {

    this.libroService.getAll().subscribe({
      next: data => {
        this.listaLibri = data as Array<Libro>;
      },
      error: err => {
        console.log(err);
      }
    });

    this.carrelloService.getUpdate().subscribe({
      next: data => {
        this.carrello = data;
      },
      error: err => {
        console.log(err);
      }
    })
  }

  aggiungiLibroAlCarrello(libroID: number) {
    this.carrelloService.aggiungiLibro(libroID, 1);
  }

  // Sorting

  cercaPerTitolo() {

    this.titoloCercato = this.titoloCercato.toLowerCase();

    this.libroService.getAll().subscribe({
      next: data => {
        this.listaLibri = data as Array<Libro>;

        if (this.titoloCercato.length == 0) {
          return;
        }

        this.listaLibri = this.listaLibri.filter((libro) => {
          return libro.titolo.toLowerCase().includes(this.titoloCercato);
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
      let autoreA = a.autore.slice(3);
      let autoreB = b.autore.slice(3);
      return this.asc ? autoreA.localeCompare(autoreB) : autoreB.localeCompare(autoreA);
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
}
