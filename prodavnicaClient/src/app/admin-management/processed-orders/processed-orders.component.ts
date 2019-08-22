import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/core/services/order.service';
import { OrderReport } from 'src/app/core/models/orderReport.model';
import { OrderItemReport } from 'src/app/core/models/orderItemReprot.model';
import { OrderSingle } from 'src/app/core/models/orderSingle.model';
import * as fileSaver from 'file-saver';

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
    this.orderService.getProcessedOrders().subscribe(
      response => {
        this.orderReport = response;        
        this.orderSingleList = response.orderDTOList;
      },
      error => {
        console.log("Error! error message:", error.error.message  );
      }
    );    
  }

  downloadPDF(){
    this.orderService.getProcessedOrdersPDF().subscribe(
      response => {
        let blob:any = new Blob([response], { type: 'application/pdf' });
        fileSaver.saveAs(blob, 'orders.pdf');       
      },
      error => {
        console.log('error message:', error.error.message);        
      }
    );
    
  }

}
