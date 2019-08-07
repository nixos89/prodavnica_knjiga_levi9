-- inserting authors
INSERT INTO author(author_id, first_name, last_name) VALUES(1, "Kenan", "Crnkic");
INSERT INTO author(author_id, first_name, last_name) VALUES(2, "Petar", "Petrovic Njegos");
INSERT INTO author(author_id, first_name, last_name) VALUES(3, "Jovan", "Jovanovic");
INSERT INTO author(author_id, first_name, last_name) VALUES(4, "JK", "Rowling");

-- Inserting books

INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(1, 33, 0, "Harry Potter", 999.99);
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(2, 151, 0, "Biblbija Novi Zavet", 1099.98);
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(3, 5, 0, "Gorski vijenac", 39.98);
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(4, 7, 0, "Pazi kojeg vuka hranis", 682.50);
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(5, 85, 0, "Uvelici uveoci", 774.99);

-- connecting author and book
INSERT INTO book_author(author_id, book_id) VALUES(1, 4);
INSERT INTO book_author(author_id, book_id) VALUES(3, 5);
INSERT INTO book_author(author_id, book_id) VALUES(4, 1);
INSERT INTO book_author(author_id, book_id) VALUES(2, 3);

-- inserting categories
INSERT INTO category(category_id, name) VALUES(1, "Motivacija");
INSERT INTO category(category_id, name) VALUES(2, "Lirika");
INSERT INTO category(category_id, name) VALUES(3, "Tragedija");
INSERT INTO category(category_id, name) VALUES(4, "Komedija");

-- connecting category and book
INSERT INTO book_category(category_id, book_id) VALUES(1, 4);
INSERT INTO book_category(category_id, book_id) VALUES(3, 5);
INSERT INTO book_category(category_id, book_id) VALUES(4, 4);
INSERT INTO book_category(category_id, book_id) VALUES(2, 1);
INSERT INTO book_category(category_id, book_id) VALUES(1, 3);