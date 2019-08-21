import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/core/services/order.service';
import { OrderReport } from 'src/app/core/models/orderReport.model';
import { OrderItemReport } from 'src/app/core/models/orderItemReprot.model';
import { OrderSingle } from 'src/app/core/models/orderSingle.model';

@Component({
  selector: 'app-processed-orders',
  templateUrl: './processed-orders.component.html',
  styleUrls: ['./processed-orders.component.css']
})
export class ProcessedOrdersComponent implements OnInit {
  orderReport: OrderReport;
  orderSingleList: OrderSingle[];
  orderItemReports: OrderItemReport[];

  constructor(private orderService: OrderService) { }

  ngOnInit() {
    this.getAllOrders();
  }

  getAllOrders(){
    console.log('pozvao se getAllOrders()');
    
    this.orderService.getProcessedOrders().subscribe(
      response => {
        this.orderReport = response;
        console.log('this.orderReport: ', this.orderReport);
        
        this.orderSingleList = response.orderDTOList;
        // this.orderSingleList = this.orderReport.orderSingleList;
        console.log('this.orderSingleList: ', this.orderSingleList);
        
      },
      error => {
        console.log("Error! error message:", error.error.message  );
        
      }
    );    
  }


  downloadPDF(){
    this.orderService.getProcessedOrdersPDF().subscribe(
      response => {
         // TODO: do  something with response or just add routerLink in HTML Template        
      },
      error => {

      }
    );
    
  }

}
