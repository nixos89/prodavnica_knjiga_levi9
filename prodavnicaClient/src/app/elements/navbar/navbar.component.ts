import { Component, OnInit } from "@angular/core";
import { OrderService } from "../../core/services/order.service";
import { TokenService } from "src/app/core/services/token.service";
import { Router } from "@angular/router";
import { AuthenticationService } from "src/app/core/services/authentication.service";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.css"]
})
export class NavbarComponent implements OnInit {
  itemsNumber: number;

  constructor(
    public orderService: OrderService,
    private tokenService: TokenService,
    public authService: AuthenticationService,
    private router: Router
  ) {}

  ngOnInit() {
    this.itemsNumber = this.orderService.getOrderItems().length;
  }

  onLogin() {
    this.router.navigate(["login"]);
  }

  onLogout() {
    // this.authService.logout().subscribe(response => {});
    this.tokenService.destroyToken();
    this.router.navigate(["/"]);
  }
}
