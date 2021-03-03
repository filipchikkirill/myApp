create sequence hibernate_sequence start 1000 increment 1;

create table audio (
    id int8 not null,
    author varchar(255),
    date_add timestamp,
    filename varchar(255) not null,
    name varchar(255),
    user_id int8,
    primary key (id)
);

create table audio_play_list (
    play_list_id int8 not null,
    audio_id int8 not null,
    primary key (play_list_id, audio_id)
);

create table base_dir (
    id int8 not null,
    date_add timestamp,
    path varchar(255),
    size int8,
    primary key (id)
);

create table dir (
    id int8 not null,
    name varchar(255),
    base_dir_id int8,
    primary key (id)
);

create table file_custom (
    id int8 not null,
    name varchar(255),
    size int8,
    base_dir_id int8,
    primary key (id)
);

create table message (
    id int8 not null,
    filename varchar(255),
    text varchar(2048),
    title varchar(255),
    user_id int8,
    primary key (id)
);

create table note (
    id int8 not null,
    note varchar(255),
    title varchar(255),
    user_id int8,
    primary key (id)
);

create table play_list (
    id int8 not null,
    date_add timestamp,
    name varchar(255),
    user_id int8,
    primary key (id)
);

create table user_role (
    user_id int8 not null,
    roles varchar(255)
);

create table usr (
    id int8 not null,
    active boolean not null,
    name varchar(255),
    password varchar(255),
    username varchar(255),
    primary key (id)
);

alter table if exists audio
    add constraint audio_user_fk
    foreign key (user_id) references usr;

alter table if exists audio_play_list
    add constraint audio_play_list_audio_fk
    foreign key (audio_id) references audio;

alter table if exists audio_play_list
    add constraint audio_play_list_play_list_fk
    foreign key (play_list_id) references play_list;

alter table if exists dir
    add constraint dir_base_dir_fk
    foreign key (base_dir_id) references base_dir;

alter table if exists file_custom
    add constraint file_custom_base_dir_fk
    foreign key (base_dir_id) references base_dir;

alter table if exists message
    add constraint message_user_fk
    foreign key (user_id) references usr;

alter table if exists play_list
    add constraint play_list_user_fk
    foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr;