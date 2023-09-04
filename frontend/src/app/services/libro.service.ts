import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class LibroService {

    URL = "http://localhost:8080/api/libro";

    constructor(private http: HttpClient) {
    }

    getAllLibri() {
        return this.http.get(this.URL + "/get/all");
    }
}
