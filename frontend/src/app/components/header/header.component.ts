import {Component, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {faClose} from "@fortawesome/free-solid-svg-icons";
import {AuthService} from "../../services/auth.service";
import {StorageService} from "../../services/storage.service";
import {UtenteService} from "../../services/utente.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  // Icons
  faClose = faClose;

  // Variables
  loginIsVisible = false;
  registerIsVisible = false;

  username: string | undefined = "";
  avatar: string | any = "";
  isLoggedIn = false;

  constructor(private authService: AuthService, private storageService: StorageService, private utenteService: UtenteService) {
  }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.username = this.storageService.getUser()?.username;
      this.avatar = this.utenteService.getAvatar(this.username!).subscribe();
    }
  }

  toggleLoginVisibility() {
    this.loginIsVisible = !this.loginIsVisible;
  }

  logout() {
    this.authService.logout();
    window.location.reload();
  }

}
