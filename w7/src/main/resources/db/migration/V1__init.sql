CREATE TABLE students (
   id IDENTITY PRIMARY KEY,
   name TEXT
);

INSERT INTO students (name)
    VALUES
        ('VASYA'),
        ('FEDYA');