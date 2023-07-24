import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./routes/home/home.component";
import {BibliotecaComponent} from "./routes/biblioteca/biblioteca.component";
import {ContattiComponent} from "./routes/contatti/contatti.component";
import {LoginComponent} from "./routes/login/login.component";

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'biblioteca',
    component: BibliotecaComponent,
  },
  {
    path: 'contatti',
    component: ContattiComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
