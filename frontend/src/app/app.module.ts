import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './components/header/header.component';
import {FooterComponent} from './components/footer/footer.component';
import {BibliotecaComponent} from './routes/biblioteca/biblioteca.component';
import {HomeComponent} from './routes/home/home.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {LoginComponent} from './components/login/login.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatDialogModule} from '@angular/material/dialog';
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgOptimizedImage} from "@angular/common";
import {HttpRequestInterceptor} from "./helpers/http.interceptor";
import {AziendaComponent} from './routes/azienda/azienda.component';
import {MatMenuModule} from '@angular/material/menu';
import { AccountComponent } from './routes/account/account.component';

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        FooterComponent,
        BibliotecaComponent,
        HomeComponent,
        LoginComponent,
        AziendaComponent,
        AccountComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        FontAwesomeModule,
        BrowserAnimationsModule,
        MatDialogModule,
        MatButtonModule,
        MatInputModule,
        NgOptimizedImage,
        MatMenuModule,
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: HttpRequestInterceptor,
            multi: true
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
