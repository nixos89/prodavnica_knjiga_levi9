import { Component, OnInit } from "@angular/core";
import { Login } from "src/app/core/models/login.model";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { ToastrService } from "ngx-toastr";
import { Router } from "@angular/router";
import { TokenService } from "src/app/core/services/token.service";
import { HttpParams } from "@angular/common/http";
import { Body } from "src/app/core/models/user.model";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {
  loginData: Login = new Login();

  constructor(
    private authService: AuthenticationService,
    private tokenService: TokenService,
    private toastr: ToastrService,
    private router: Router
  ) {}
  ngOnInit() {}

  onLogin() {
    const body: Body = new Body(this.loginData.username, this.loginData.password);

    const formData = new FormData();
    formData.append("username", this.loginData.username);
    formData.append("password", this.loginData.password);
    formData.append("grant_type", "password");

    const params = new HttpParams({
      fromObject: {
        grant_type: "password",
        username: this.loginData.username,
        password: this.loginData.password
      }
    });

    console.log(body);

    this.authService.login(body).subscribe(
      response => {
        console.log(response);
        // this.tokenService.saveToken(response.acess_token);
        this.router.navigate(["/"]);
      },
      error => {
        this.toastr.error("Incorrect username or password", "Unable to login");
      }
    );
  }
}
