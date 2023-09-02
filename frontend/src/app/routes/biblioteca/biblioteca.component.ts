import {Component, OnInit} from '@angular/core';
import {LibroService} from "../../services/libro.service";
import {Carrello} from "../../models/carrello.model";
import {Libro} from "../../models/libro.model";
import {CarrelloService} from "../../services/carrello.service";
import { faSearch } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-biblioteca',
  templateUrl: './biblioteca.component.html',
  styleUrls: ['./biblioteca.component.scss']
})
export class BibliotecaComponent implements OnInit {

  listaLibri: Array<Libro> | null = null;
  carrello: Carrello | null = null;

  // Icone
  faSearch = faSearch;

  constructor(private libroService: LibroService, private carrelloService: CarrelloService) {
  }

  ngOnInit(): void {

    this.libroService.getAllLibri().subscribe({
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

  protected readonly alert = alert;
}
