import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./routes/home/home.component";
import {BibliotecaComponent} from "./routes/biblioteca/biblioteca.component";
import {ContattiComponent} from "./routes/contatti/contatti.component";
import {ServiziComponent} from "./routes/servizi/servizi.component";

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
    path: 'servizi',
    component: ServiziComponent,
  },
  {
    path: 'contatti',
    component: ContattiComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
