import {Component, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {faClose} from "@fortawesome/free-solid-svg-icons";
import {AuthService} from "../../services/auth.service";
import {StorageService} from "../../services/storage.service";

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

  username: string | undefined = "";
  isLoggedIn = false;

  constructor(private authService: AuthService, private storageService: StorageService) {
  }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.username = this.storageService.getUser()?.username;
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
