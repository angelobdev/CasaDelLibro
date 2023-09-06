import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Libro} from "../models/libro.model";

@Injectable({
  providedIn: 'root'
})
export class LibroService {

  URL = "http://localhost:8080/api/libro";

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<Array<Libro>>(this.URL + "/get/all");
  }
}
