import {Component, OnInit} from '@angular/core';
import {CarrelloService} from "../../services/carrello.service";
import {StorageService} from "../../services/storage.service";
import {Carrello} from "../../models/carrello.model";
import {faTrash} from "@fortawesome/free-solid-svg-icons";

@Component({
    selector: 'app-carrello',
    templateUrl: './carrello.component.html',
    styleUrls: ['./carrello.component.scss']
})
export class CarrelloComponent implements OnInit {

    utenteID = -1;
    carrello: Carrello | null = null;

    // Icone
    faRemove = faTrash;

    constructor(private carrelloService: CarrelloService, private storageService: StorageService) {

    }

    ngOnInit(): void {
        this.utenteID = this.storageService.getUtente().id;
        this.carrelloService.getUpdate().subscribe({
            next: data => {
                this.carrello = data;
            },
            error: err => {
                console.log(err);
            }
        })
    }

    acquistaCarrello() {
        console.log("Acquistato");
    }

    rimuoviLibro(libroID: number) {
        this.carrelloService.rimuoviLibro(libroID);
    }

    svuotaCarrello() {
        this.carrelloService.svuotaCarrello();
    }

}
