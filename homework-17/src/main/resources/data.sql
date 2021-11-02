INSERT INTO GENRES (ID, NAME) VALUES
(1, 'horror'),
(2, 'fantasy'),
(3, 'humour'),
(4, 'novel')
;

INSERT INTO AUTHORS (ID, FULL_NAME) VALUES
(1, 'Lev Nikolaevich Tolstoy'),
(2, 'Stephen Edwin King')
;

INSERT INTO BOOKS (ID, TITLE, AUTHOR_ID, GENRE_ID) VALUES
(1, 'War and peace', 1, 4)
