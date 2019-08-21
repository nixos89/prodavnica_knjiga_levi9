import { Injectable } from "@angular/core";
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from "rxjs";
import { environment } from "src/environments/environment.prod";
import {AuthToken} from '../models/authToken.model';

@Injectable({
  providedIn: "root"
})
export class AuthenticationService {
  constructor(private http: HttpClient) {}

  getAccessToken(username: string, password: string): Observable<AuthToken> {

    let oauth2_token_endpoint = environment.url + "oauth/token";
    let oauth2_client_id = 'BookStore';
    let oauth2_client_secret = 'secret';
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
      })
    };
    const body = 'client_id={0}&client_secret={1}&grant_type=password&username={2}&password={3}'
      .replace('{0}', oauth2_client_id)
      .replace('{1}', oauth2_client_secret)
      .replace('{2}', username)
      .replace('{3}', password);

    return this.http.post<AuthToken>(oauth2_token_endpoint, body, httpOptions);
  }
}
