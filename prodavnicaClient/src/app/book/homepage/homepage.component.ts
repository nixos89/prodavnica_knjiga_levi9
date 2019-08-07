import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/core/models/book.model';
import { HttpClient } from '@angular/common/http';
import { AdminManagementService } from 'src/app/core/services/admin-management.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  top10Books: Book[]; // = getTop10Books();

  title = "Recepti prema Bozjim zapovestima";
  // url = 'localhost:4200/api/top10Books';

  

  constructor(private adminMangService: AdminManagementService) {

  }

  ngOnInit() {

  }

  getTop10Books() {
    
  }

  showConfig() {
    // this.adminMangService.getConfig()
    //   .subscribe((data: Config) => this.config = {
    //       heroesUrl: data['heroesUrl'],
    //       textfile:  data['textfile']
    //   });
  }


}
