drop table if exists user;
drop table if exists remind;

create table user
(
    user_id  int primary key auto_increment,
    username varchar(40)  not null unique,
    email    varchar(40)  not null,
    password varchar(108) not null
);

create table remind
(
    remind_id     int primary key auto_increment,
    user_id       int          not null,
    description   varchar(100) not null,
    remind_time   datetime,
    complete_flag boolean default false
);