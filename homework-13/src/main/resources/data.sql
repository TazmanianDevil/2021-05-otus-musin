INSERT INTO GENRES (NAME) VALUES
('horror'),
('fantasy'),
('humour'),
('novel')
;

INSERT INTO AUTHORS (FULL_NAME) VALUES
('Lev Nikolaevich Tolstoy'),
('Stephen Edwin King')
;

INSERT INTO BOOKS (TITLE, AUTHOR_ID, GENRE_ID) VALUES
('War and peace', 1, 4);

INSERT INTO USERS (NAME, PASSWORD, AUTHORITY) VALUES
('user', 'user', 'READER'),
('librarian', 'password', 'EDITOR'),
('admin', 'strong-password', 'ADMIN');

