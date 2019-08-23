import { OrderItemReport } from './orderItemReprot.model';

export class OrderSingle {
    orderId: number;
    orderItemDTOList: OrderItemReport[];
    orderDate: string;
    orderPrice: number;

    constructor() {
        this.orderId = 0;
        this.orderItemDTOList = [];
        this.orderDate = "";
        this.orderPrice = 0;
    }
}