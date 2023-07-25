import {Component} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {faClose} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  // Icons
  faClose = faClose;

  // Variables
  loginIsVisible = false;

  constructor(public loginDialog: MatDialog) {
  }

  toggleLoginVisibility() {
    this.loginIsVisible = !this.loginIsVisible;
  }

}
