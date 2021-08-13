drop table if exists remind;
drop table if exists userEntity;

create table userEntity
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
    priority      int,
    remind_time   datetime,
    complete_flag boolean default false,
    constraint UID foreign key (user_id) references userEntity (user_id)
);