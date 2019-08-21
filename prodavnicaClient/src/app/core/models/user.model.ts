export class Body {
  username: String;
  password: String;
  grant_type: String;

  constructor(username, password) {
    this.username = username;
    this.password = password;
    this.grant_type = "password";
  }
}
