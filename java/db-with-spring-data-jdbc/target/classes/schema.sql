DROP TABLE voyage IF EXISTS;

CREATE TABLE voyage (
    id serial primary key,
    destination varchar(255),
    duree_jour int,
    created_at timestamp,
    updated_at timestamp
);