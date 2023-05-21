drop table if exists students;

create table students
(
    id      int auto_increment primary key,
    name    varchar(250) not null,
    surname varchar(250) not null
);

create table users (
    username varchar(50) not null primary key,
    password varchar(250) not null,
    enabled boolean not null
);

create table authorities (
    id int auto_increment primary key,
    username varchar(50) not null,
    authority varchar(50) not null,
    foreign key (username) references users (username)
);