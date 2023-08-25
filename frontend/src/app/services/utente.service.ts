import { Injectable } from '@angular/core';
import {Utente} from "../models/utente.model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UtenteService {

  URL = "http://localhost:8080/utent"

  constructor(private http: HttpClient) { }

  // Metodi CRUD:

  createUtente(utente: Utente){

  }

  getUtente(id: number) {

  }

  getAllUtenti(){

  }

  updateUtente(id:number, utente: Utente){

  }

  deleteUtente(id: number){

  }
}
