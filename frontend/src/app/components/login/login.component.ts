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

  loginForm: LoginRequest = {
    username: '',
    password: '',
    remember: false
  };

  registerForm: any = {
    nome: null,
    cognome: null,
    dataNascita: null,
    username: null,
    email: null,
    password: null,
    rpassword: null,
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

  loadError(error: string) {
    this.errorMessage = error;
    this.isLoginFailed = true;

    setTimeout(() => {
      this.errorMessage = "";
    }, 5000);
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
        this.loadError(err.error.message);
      }
    });
  }

  onRegisterSubmit(): void {

    console.log(this.registerForm);

    // Controllo le password
    if (this.registerForm.password != this.registerForm.rpassword) {
      this.loadError("Le password non corrispondono");
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
        this.loadError(err.error.message);
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
