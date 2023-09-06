import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthService} from "../services/auth.service";

export const authGuard: CanActivateFn = (route, _) => {
    let authService = inject(AuthService);
    let router = inject(Router);
    let subURL = route.routeConfig!.path!;

    if (!authService.isLoggedIn()) {
        switch (subURL) {
            case "account":
                router.navigate(['']).then(() => {
                    alert("Non puoi accedere a questo URL!");
                });
                return false;
            default:
                return true;
        }
    }

    return true;
};

