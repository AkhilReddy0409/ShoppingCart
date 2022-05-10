import { AddressModel } from "./address-model";

export class UserModel {
    constructor(
        public profileId: number,
        public fullName: string,
        public emailId: string,
        public number : number,
        public gender: string,
        public role: string,
        public password: string,
        public address: AddressModel
    ){}
}
