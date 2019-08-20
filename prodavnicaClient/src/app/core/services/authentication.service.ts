import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment.prod";

@Injectable({
  providedIn: "root"
})
export class AuthenticationService {
  constructor(private http: HttpClient) {}

  login(loginPayload): Observable<any> {
    const headers = {
      Authorization: "Basic " + btoa("BookStore" + ":" + "secret"),
      "Content-type": "application/x-www-form-urlencoded"
    };
    return this.http.post(environment.url + "oauth/token", loginPayload, {
      headers
    });
  }
}
