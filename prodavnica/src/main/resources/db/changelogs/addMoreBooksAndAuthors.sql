INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(6, 15, 0, "Simboli moci", 29.98);
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(7, 140, 0, "Besnilo", 19.50);
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(8, 85, 0, "I ne osta ni jedan", 23.50);
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(9, 110, 0, "The History Of Serbia", 19.00);
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(10, 666, 0, "Strukture podataka i algoritmi", 16.66);

INSERT INTO author(author_id, first_name, last_name) VALUES(5, "Djura", "Paunic");
INSERT INTO author(author_id, first_name, last_name) VALUES(6, "Mirjana", "Ivanovic");
INSERT INTO author(author_id, first_name, last_name) VALUES(7, "Cedomir", "Antic");
INSERT INTO author(author_id, first_name, last_name) VALUES(8, "Agata", "Kristi");
INSERT INTO author(author_id, first_name, last_name) VALUES(9, "Borislav", "Pekic");
INSERT INTO author(author_id, first_name, last_name) VALUES(10, "Den", "Brown");

INSERT INTO book_author(author_id, book_id) VALUES(10, 6);
INSERT INTO book_author(author_id, book_id) VALUES(5, 10);
INSERT INTO book_author(author_id, book_id) VALUES(6, 10);
INSERT INTO book_author(author_id, book_id) VALUES(7, 9);
INSERT INTO book_author(author_id, book_id) VALUES(9, 7);
INSERT INTO book_author(author_id, book_id) VALUES(8, 8);

INSERT INTO book_category(category_id, book_id) VALUES(9, 8);
INSERT INTO book_category(category_id, book_id) VALUES(19, 10);
INSERT INTO book_category(category_id, book_id) VALUES(10, 6);
INSERT INTO book_category(category_id, book_id) VALUES(2, 7);
INSERT INTO book_category(category_id, book_id) VALUES(14, 9);
