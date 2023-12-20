CREATE TYPE user_status AS ENUM ('ADMIN', 'USER');
CREATE TYPE institution_type AS ENUM ('RESTAURANT', 'CAFE');


CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT PRIMARY KEY,
                                     user_name VARCHAR(255) NOT NULL,
                                     password VARCHAR(255) NOT NULL,
                                     mail VARCHAR(255) UNIQUE NOT NULL,
                                     phone VARCHAR(255) UNIQUE NOT NULL,
                                     status user_status NOT NULL
);
INSERT INTO users (id, user_name, password, mail, phone, status)
VALUES (1, 'qwe2', 'asd2', 'qw2e', '1233', 'ADMIN');



CREATE TABLE IF NOT EXISTS institutions (
                                            id BIGINT PRIMARY KEY,
                                            name VARCHAR(255),
                                            type institution_type,
                                            address VARCHAR(255),
                                            phone VARCHAR(255),
                                            mail VARCHAR(255),
                                            description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS institution_tables (
                                                  id BIGINT PRIMARY KEY,
                                                  table_number INT UNIQUE,
                                                  count_of_chairs INT NOT NULL,
                                                  institution_id BIGINT REFERENCES institutions(id)
);
CREATE TABLE IF NOT EXISTS photos (
                                      id BIGINT PRIMARY KEY,
                                      institution_id BIGINT REFERENCES institutions(id),
                                      url VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS reservations (
                                            id BIGINT PRIMARY KEY,
                                            user_id BIGINT REFERENCES users(id),
                                            institution_id BIGINT REFERENCES institutions(id),
                                            table_id BIGINT REFERENCES institution_tables(id),
                                            start_time TIMESTAMP NOT NULL,
                                            end_time TIMESTAMP NOT NULL
);

