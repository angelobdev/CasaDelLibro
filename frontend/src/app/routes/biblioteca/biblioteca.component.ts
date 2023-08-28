import {Component, OnInit} from '@angular/core';
import {LibroService} from "../../services/libro.service";

@Component({
    selector: 'app-biblioteca',
    templateUrl: './biblioteca.component.html',
    styleUrls: ['./biblioteca.component.scss']
})
export class BibliotecaComponent implements OnInit {

    listaLibri: any;

    constructor(private libriService: LibroService) {
    }

    ngOnInit(): void {
        this.libriService.getAllLibri().subscribe({
            next: data => {
                this.listaLibri = data;
            },
            error: err => {
                console.log(err);
            }
        })
    }


    protected readonly LibroService = LibroService;
}
