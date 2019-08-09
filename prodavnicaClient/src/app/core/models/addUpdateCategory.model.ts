export class AddUpdateCategory {
  name: string;
  isDeleted:boolean;

  constructor() {
    this.isDeleted = false;
    this.name = "";
  }
}
