drop table if exists remind;
create table remind (
    remind_id int primary key auto_increment,
    description varchar(100) not null,
    remind_time datetime,
    complete_flag boolean default false
);