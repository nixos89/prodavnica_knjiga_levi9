-- inserting authors
INSERT INTO author(author_id, first_name, last_name) VALUES(1, "Agatha", "Christie");
INSERT INTO author(author_id, first_name, last_name) VALUES(2, "Stephen", "King");
INSERT INTO author(author_id, first_name, last_name) VALUES(3, "JRR", "Tolkien");
INSERT INTO author(author_id, first_name, last_name) VALUES(4, "JK", "Rowling");

-- Inserting books

INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(1, 33, 0, "Harry Potter and the Philosopher's Stone", 29.99);
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(2, 151, 0, "Murder on the Orient Express", 19.98); #AGATA
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(3, 5, 0, "The murder of Roger Ackroyd", 20.98); #AGATA
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(4, 7, 0, "Harry Potter and the Chamber of Secrets", 15.50);
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(5, 85, 0, "Harry Potter and the Prisoner of Azkaban", 14.99);

-- connecting author and book
INSERT INTO book_author(author_id, book_id) VALUES(1, 2);
INSERT INTO book_author(author_id, book_id) VALUES(1, 3);
INSERT INTO book_author(author_id, book_id) VALUES(4, 1);
INSERT INTO book_author(author_id, book_id) VALUES(4, 5);
INSERT INTO book_author(author_id, book_id) VALUES(4, 4);

-- inserting categories
INSERT INTO category(category_id, name, is_deleted) VALUES(1, "Motivation", 0);
INSERT INTO category(category_id, name, is_deleted) VALUES(2, "Lyric", 0);
INSERT INTO category(category_id, name, is_deleted) VALUES(3, "Tragedy", 0);
INSERT INTO category(category_id, name, is_deleted) VALUES(4, "Comedy", 0);

-- connecting category and book
INSERT INTO book_category(category_id, book_id) VALUES(2, 1);
INSERT INTO book_category(category_id, book_id) VALUES(1, 4);
INSERT INTO book_category(category_id, book_id) VALUES(4, 4);
INSERT INTO book_category(category_id, book_id) VALUES(3, 5);
