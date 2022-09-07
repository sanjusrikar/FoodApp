import { Timestamp } from "rxjs-compat";
import { Food } from "./food";

export class FoodOrder {
    order_Id: number;
    email: string;
    name: string;
    status: string;
    total_price: number;
    timeStamp: string;
    foods : Food[];
}
