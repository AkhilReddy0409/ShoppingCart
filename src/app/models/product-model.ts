export class ProductModel {
    constructor(
        public id: number,
        public productName: string,
        public productCategory: string,
        public productDescription: string,
        public image: string,
        public productPrice: number
        
    ) {}
}
