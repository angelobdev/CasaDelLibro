import {CanActivateFn} from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  // TODO: check ruolo
  console.log(route, state);
  return true;
};
