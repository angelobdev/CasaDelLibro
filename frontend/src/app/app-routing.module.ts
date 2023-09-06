import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./routes/home/home.component";
import {BibliotecaComponent} from "./routes/biblioteca/biblioteca.component";
import {AziendaComponent} from "./routes/azienda/azienda.component";
import {AccountComponent} from "./routes/account/account.component";
import {authGuard} from "./helpers/auth.guard";

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [authGuard]
  },
  {
    path: 'biblioteca',
    component: BibliotecaComponent,
    canActivate: [authGuard]
  },
  {
    path: 'azienda',
    component: AziendaComponent,
    canActivate: [authGuard]
  },
  {
    path: 'account',
    component: AccountComponent,
    canActivate: [authGuard]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
