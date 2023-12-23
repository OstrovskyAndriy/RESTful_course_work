-- Створення таблиці user_statuses
CREATE TABLE IF NOT EXISTS user_statuses (
                                             id SERIAL PRIMARY KEY,
                                             name VARCHAR(255) UNIQUE NOT NULL
    );

-- Додавання значень до таблиці user_statuses
INSERT INTO user_statuses (name) VALUES
                                     ('ADMIN'),
                                     ('USER');

-- Створення таблиці institution_types
CREATE TABLE IF NOT EXISTS institution_types (
                                                 id SERIAL PRIMARY KEY,
                                                 name VARCHAR(255) UNIQUE NOT NULL
    );

-- Додавання значень до таблиці institution_types
INSERT INTO institution_types (name) VALUES
                                         ('RESTAURANT'),
                                         ('CAFE');

CREATE TABLE IF NOT EXISTS users (
                                     id bigserial PRIMARY KEY,
                                     user_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    mail VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(255) UNIQUE NOT NULL,
    status_id INT REFERENCES user_statuses(id) NOT NULL
    );
INSERT INTO users ( user_name, password, mail, phone, status_id)
VALUES( 'username1', 'password1', 'user1@example.com', '1234567890', 1);

CREATE TABLE IF NOT EXISTS institutions (
                                            id bigserial PRIMARY KEY,
                                            name VARCHAR(255),
    address VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(255) UNIQUE NOT NULL,
    mail VARCHAR(255) UNIQUE NOT NULL,
    description VARCHAR(255) NOT NULL,
    type_id INT REFERENCES institution_types(id) NOT NULL
    );
INSERT INTO institutions (name, address, phone, mail, description, type_id)
VALUES ('Назва закладу', 'Адреса', 'Телефон', 'Пошта', 'Опис', 1);




CREATE TABLE IF NOT EXISTS institution_tables (
                                                  id BIGINT PRIMARY KEY,
                                                  table_number INT UNIQUE,
                                                  count_of_chairs INT NOT NULL,
                                                  institution_id BIGINT REFERENCES institutions(id) NOT NULL
    );


CREATE TABLE IF NOT EXISTS photos (
                                      id BIGINT PRIMARY KEY,
                                      institution_id BIGINT REFERENCES institutions(id),
    url VARCHAR(255) UNIQUE NOT NULL
    );

CREATE TABLE IF NOT EXISTS reservations (
                                            id BIGINT PRIMARY KEY,
                                            user_id BIGINT REFERENCES users(id) NOT NULL,
    institution_id BIGINT REFERENCES institutions(id) NOT NULL,
    table_id BIGINT REFERENCES institution_tables(id) NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL
    );