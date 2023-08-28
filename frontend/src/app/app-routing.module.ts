import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./routes/home/home.component";
import {BibliotecaComponent} from "./routes/biblioteca/biblioteca.component";
import {AziendaComponent} from "./routes/azienda/azienda.component";

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
        path: 'azienda',
        component: AziendaComponent,
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
