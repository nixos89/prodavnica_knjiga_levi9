
import { OrderSingle } from './orderSingle.model';

export class OrderReport {
    orderDTOList: OrderSingle[];

    constructor() {
        this.orderDTOList = [];
    }
}