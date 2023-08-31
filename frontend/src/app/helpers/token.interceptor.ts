import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';

import {StorageService} from '../services/storage.service';

@Injectable({
    providedIn: 'root',
})
export class TokenInterceptor implements HttpInterceptor {

    constructor(private storageService: StorageService) {
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        // Carico l'utente
        let jwt = this.storageService.getJWToken();

        // Se esiste (loggato) aggiungo le credenziali alla richiesta HTTP
        if (jwt) {
            let token = jwt.token;

            request = request.clone({
                withCredentials: true,
                setHeaders: {
                    Authorization: "Bearer " + token,
                }
            });
        }

        return next.handle(request).pipe(
            catchError((error) => {
                // TODO: logout when token is expired!
                return throwError(() => error);
            })
        );

    }

}
