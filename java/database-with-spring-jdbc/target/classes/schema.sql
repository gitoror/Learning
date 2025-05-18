DROP TABLE voyage IF EXISTS;

CREATE TABLE voyage (
    id serial,
    destination varchar(255),
    duree_jour int
);