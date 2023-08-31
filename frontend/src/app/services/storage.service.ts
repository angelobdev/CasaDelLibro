import {Injectable} from '@angular/core';
import {JwtResponse} from "../payload/response/jwt.response";

@Injectable({
    providedIn: 'root'
})
export class StorageService {

    JWT_KEY = 'auth-token';

    public saveJWToken(jwt: JwtResponse): void {
        window.sessionStorage.removeItem(this.JWT_KEY);
        window.sessionStorage.setItem(this.JWT_KEY, JSON.stringify(jwt));
    }

    public getJWToken(): JwtResponse | null {
        const jwtString = window.sessionStorage.getItem(this.JWT_KEY);
        if (jwtString)
            return JSON.parse(jwtString) as JwtResponse;
        return null;
    }

    public clean(): void {
        window.sessionStorage.clear();
    }
}
