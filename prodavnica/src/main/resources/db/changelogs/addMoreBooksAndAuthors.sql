INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(6, 15, 0, "The Da Vinci Code", 29.98);  #DB
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(7, 140, 0, "Inferno", 19.50); # DB
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(8, 85, 0, "The Lord of the Rings", 23.50); #TOLKIN
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(9, 110, 0, "The Hobbit or There and Back Again", 19.00); #TOLKIN
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(10, 43, 0, "Anna Karenina", 17.18); #L T
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(11, 11, 0, "War and Peace", 16.52); #Tolsto
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(12, 23, 0, "The Death of Ivan Ilych ", 26.33); #Tolsto
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(13, 54, 0, "Carrie", 19.17);  # SK
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(14, 68, 0, "The Trial", 13.46); #FK
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(15, 80, 0, "IT", 10.74);  # SK
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(16, 123, 0, "Boris Godunov", 23.82);
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(17, 140, 0, "Hamlet", 22.61);

INSERT INTO author(author_id, first_name, last_name) VALUES(5, "Leo", "Tolstoy");
INSERT INTO author(author_id, first_name, last_name) VALUES(6, "Franz", "Kafka");
INSERT INTO author(author_id, first_name, last_name) VALUES(7, "Alexander", "Pushkin");
INSERT INTO author(author_id, first_name, last_name) VALUES(8, "William", " Shakespeare");
INSERT INTO author(author_id, first_name, last_name) VALUES(9, "Dan", "Brown");

INSERT INTO book_author(author_id, book_id) VALUES(8, 17);
INSERT INTO book_author(author_id, book_id) VALUES(7, 16);
INSERT INTO book_author(author_id, book_id) VALUES(2, 15);
INSERT INTO book_author(author_id, book_id) VALUES(2, 13);
INSERT INTO book_author(author_id, book_id) VALUES(6, 14);
INSERT INTO book_author(author_id, book_id) VALUES(5, 12);
INSERT INTO book_author(author_id, book_id) VALUES(5, 11);
INSERT INTO book_author(author_id, book_id) VALUES(5, 10);
INSERT INTO book_author(author_id, book_id) VALUES(3, 9);
INSERT INTO book_author(author_id, book_id) VALUES(3, 8);
INSERT INTO book_author(author_id, book_id) VALUES(8, 7);
INSERT INTO book_author(author_id, book_id) VALUES(8, 6);

INSERT INTO book_category(category_id, book_id) VALUES(9, 2);
INSERT INTO book_category(category_id, book_id) VALUES(9, 3);
INSERT INTO book_category(category_id, book_id) VALUES(2, 7);
INSERT INTO book_category(category_id, book_id) VALUES(10, 6);
INSERT INTO book_category(category_id, book_id) VALUES(9, 8);
INSERT INTO book_category(category_id, book_id) VALUES(14, 9);
INSERT INTO book_category(category_id, book_id) VALUES(5, 10);
INSERT INTO book_category(category_id, book_id) VALUES(8, 11);
INSERT INTO book_category(category_id, book_id) VALUES(11, 12);
INSERT INTO book_category(category_id, book_id) VALUES(5, 13);
INSERT INTO book_category(category_id, book_id) VALUES(9, 14);
INSERT INTO book_category(category_id, book_id) VALUES(5, 15);
INSERT INTO book_category(category_id, book_id) VALUES(5, 16);
INSERT INTO book_category(category_id, book_id) VALUES(7, 17);
