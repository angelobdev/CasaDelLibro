import {Component, OnInit} from '@angular/core';
import {faBars, faClose, faShoppingCart} from "@fortawesome/free-solid-svg-icons";
import {AuthService} from "../../services/auth.service";
import {StorageService} from "../../services/storage.service";
import {UtenteService} from "../../services/utente.service";
import {animate, style, transition, trigger} from '@angular/animations';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.scss'],
    animations: [
        trigger("onOff", [
            transition(":enter", [
                style({opacity: 0, transform: "translateX(100%)"}),
                animate(
                    "750ms ease-in-out",
                    style({opacity: 1, transform: "translateX(0)"})
                )
            ]),
            transition(":leave", [
                style({opacity: 1, transform: "translateX(0)"}),
                animate(
                    "600ms ease-in-out",
                    style({opacity: 0, transform: "translateX(100%)"})
                )
            ])
        ])
    ]
})
export class HeaderComponent implements OnInit {

    // Icons
    faClose = faClose;
    faBars = faBars;
    faCart = faShoppingCart;

    // Variables
    loginIsVisible = false;
    cartIsVisible = false;

    username: string | undefined = "";
    avatar: string | undefined = "";
    isLoggedIn = false;

    constructor(private authService: AuthService, private storageService: StorageService, private utenteService: UtenteService) {
    }

    ngOnInit(): void {
        if (this.authService.isLoggedIn()) {
            this.isLoggedIn = true;
            this.username = this.storageService.getJWToken()!.username;
            this.utenteService.getAvatar(this.username!).subscribe({
                next: data => {
                    this.avatar = data.message;
                },
            });
        }
    }

    toggleLoginVisibility() {
        this.loginIsVisible = !this.loginIsVisible;
    }

    toggleCartVisibility() {
        this.cartIsVisible = !this.cartIsVisible;
    }

    logout() {
        this.authService.logout();
        window.location.reload();
    }

}
