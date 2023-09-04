import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {StorageService} from "../../services/storage.service";
import {RegisterRequest} from "../../payload/requests/register.request";
import {LoginRequest} from "../../payload/requests/login.request";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  isLoginShowing = true;

  loginForm: any = {
    username: null,
    password: null,
    remember: null
  };

  registerForm: any = {
    nome: null,
    cognome: null,
    dataNascita: null,
    username: null,
    email: null,
    password: null,
    rpassword: null, // TODO: check
  }

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private authService: AuthService, private storageService: StorageService) {
  }

  ngOnInit(): void {
    if (this.authService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.roles = this.storageService.getJWToken()!.roles;
    }
  }

  onLoginSubmit(): void {

    let data: LoginRequest = this.loginForm;

    this.authService.login(data).subscribe({
      next: data => {
        this.storageService.saveJWToken(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.storageService.getJWToken()!.roles;
        this.reloadPage();
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    });
  }

  onRegisterSubmit(): void {

    console.log(this.registerForm);

    // Controllo le password
    if (this.registerForm.password != this.registerForm.rpassword) {
      this.errorMessage = "Le password non corrispondono";
      return;
    }

    // Invio la richiesta di registrazione alla REST API
    let data: RegisterRequest = this.registerForm;

    this.authService.register(data).subscribe({
      next: data => {
        this.storageService.saveJWToken(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.storageService.getJWToken()!.roles;
        this.reloadPage();
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    });

  }

  switchLoginRegister() {
    this.isLoginShowing = !this.isLoginShowing;
  }

  reloadPage(): void {
    window.location.reload();
  }
}
