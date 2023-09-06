import {Component, OnInit} from '@angular/core';
import {UtenteService} from "../../services/utente.service";
import {Utente} from "../../models/utente.model";
import {StorageService} from "../../services/storage.service";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  dataLoaded = false;
  utente: Utente | null = null;
  utenteJSON: string = '';

  constructor(private authService: AuthService, private storageService: StorageService, private utenteService: UtenteService) {
  }

  ngOnInit(): void {

    // Carico le informazioni dell'utente
    this.utenteService.get(this.storageService.getJWToken()!.id).subscribe({
      next: utente => {
        this.utente = utente;
        this.utenteJSON = JSON.stringify(this.utente);
        this.dataLoaded = true;
      },
      error: err => {
        console.log(err);
      }
    });
  }

  aggiornaUtente() {

    // Controllo che l'oggetto sia stato modificato
    if (JSON.stringify(this.utente) != this.utenteJSON) {

      // Aggiorno l'utente
      this.utenteService.update(this.utente!.id, this.utente!).subscribe({
        next: _ => {
          alert("Riaccedi per favore");
          this.authService.logout();
        },
        error: err => {
          console.log(err);
        }
      });

    } else {
      alert("Devi modificare almeno un campo prima di poter salvare!");
    }

  }

}
