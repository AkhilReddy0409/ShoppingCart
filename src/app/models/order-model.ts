import { ItemModel } from "./item-model";

export class OrderModel {
    constructor(
        public id:number,
        public userId: number,
        public amount: number,
        public items: Map<Number, ItemModel>,
        public date: any,
        public status: string
    ){}
}
