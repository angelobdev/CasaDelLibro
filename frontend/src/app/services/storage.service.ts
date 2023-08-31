import {Injectable} from '@angular/core';

const USER_KEY = 'auth-user';

@Injectable({
    providedIn: 'root'
})
export class StorageService {
    constructor() {
    }

    clean(): void {
        window.sessionStorage.clear();
    }

    public salvaUtente(user: any): void {
        window.sessionStorage.removeItem(USER_KEY);
        window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
    }

    public getUtente(): any {
        const user = window.sessionStorage.getItem(USER_KEY);

        if (user) {
            return JSON.parse(user);
        }
        return null;
    }

    public isLoggedIn(): boolean {
        const user = window.sessionStorage.getItem(USER_KEY);
        return !!user;

    }
}
