SELECT
    name_1 AS 'Фильм 1',
    datetime(start_1, 'unixepoch') AS 'Начало',
    strftime('%H:%M', length_1, 'unixepoch') AS 'прод. 1',
    name_2 AS 'Фильм 2',
    datetime(start_2, 'unixepoch') AS 'Начало',
    strftime('%H:%M', length_2, 'unixepoch') AS 'прод. 2',
    (dif / 60) AS 'Интервал(м)'
FROM (
    SELECT
        current.name AS name_1,
        current.start AS start_1,
        current.length AS length_1,
        next.name AS name_2,
        next.start AS start_2,
        next.length AS length_2,
        (next.start - current.start - current.length) as dif
    FROM (
        SELECT sessions.id, films.name AS name, start, films.length AS length
        FROM sessions
        LEFT JOIN films on film_id = films.id
    ) AS current
    INNER JOIN (
        SELECT sessions.id, films.name AS name, start, films.length AS length
        FROM sessions
        LEFT JOIN films on film_id = films.id
    ) AS next ON current.id + 1 = next.id
    ORDER BY dif
);
