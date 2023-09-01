import {CanActivateFn} from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
    console.log(route, state);
  // TODO: check ruolo
    return true;
};
