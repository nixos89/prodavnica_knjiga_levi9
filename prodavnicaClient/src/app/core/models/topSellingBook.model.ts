import { AuthorInfo } from './authorInfo.model';

export class TopSellingBook {
    bookName: string;
    authors: AuthorInfo;
    amount: number;

    constructor(){
        this.bookName = "";
        this.authors = new AuthorInfo();
        this.amount = 0;
    }
}