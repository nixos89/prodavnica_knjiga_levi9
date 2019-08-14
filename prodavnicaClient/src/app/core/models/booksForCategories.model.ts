/**
 * created for displaying books for chosen categories
 */
export class BooksForCategories {
    categoryId?: number;
    name: string;
    checked?: boolean;
    books?: [];
    selectedBooksForCategories?: [];

    constructor(categoryId: number, name: string, checked: boolean, books: [], selectedBooksForCategories: []) {
        this.categoryId = categoryId;
        this.name = name;
        this.checked = checked;
        this.books = books;
        this.selectedBooksForCategories = selectedBooksForCategories;

        if (selectedBooksForCategories) {
            
        }
    }

}
