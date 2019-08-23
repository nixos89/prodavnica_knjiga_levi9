import { Component, OnInit } from "@angular/core";
import { Login } from "src/app/core/models/login.model";
import { AuthenticationService } from "src/app/core/services/authentication.service";
import { ToastrService } from "ngx-toastr";
import { Router } from "@angular/router";
import { TokenService } from "src/app/core/services/token.service";

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
    this.authService.getAccessToken(this.loginData.username,this.loginData.password).subscribe(
      response => {
        this.tokenService.saveToken(response.access_token);
         this.router.navigate(["/"]);
      },
      error => {
        this.toastr.error("Incorrect username or password", "Unable to login");
      }
    );
  }
}
