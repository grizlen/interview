INSERT INTO films
    (name, length)
    VALUES
        ('Film1', 60 * 60),
        ('Film2', 90 * 60),
        ('Film3', 125 * 60),
        ('Film4', 60 * 60),
        ('Film5', 90 * 60),
        ('Film6', 120 * 60);

INSERT INTO sessions
    (start, film_id, price_id)
values
    (unixepoch('2022-05-01 18:00:00'), 1, 1),
    (unixepoch('2022-05-01 20:00:00'), 3, 1),
    (unixepoch('2022-05-01 22:00:00'), 2, 1),
    (unixepoch('2022-05-02 18:00:00'), 1, 2),
    (unixepoch('2022-05-02 20:00:00'), 3, 1),
    (unixepoch('2022-05-02 22:00:00'), 2, 1);

INSERT INTO prices
    (amount)
VALUES
    (100),
    (200);

INSERT INTO tickets
    (session_id)
VALUES
    (1),
    (1),
    (2),
    (4);
