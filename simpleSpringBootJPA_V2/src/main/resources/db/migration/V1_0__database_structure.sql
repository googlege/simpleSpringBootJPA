
    create table app_user (
       id int8 not null,
        user_password varchar(10) not null,
        user_name varchar(10) not null,
        version int4 not null,
        user_info_id_fk int8 not null,
        primary key (id)
    );

    create table app_user_info (
       id int8 not null,
        email varchar(100),
        name varchar(60),
        version int4 not null,
        vorname varchar(60),
        primary key (id)
    );

    create table app_user_right (
       id int8 not null,
        user_right int4 not null,
        version int4 not null,
        user_id_fk int8 not null,
        primary key (id)
    );

    create table bt_sequences (
       sequencename varchar(255) not null,
        sequencevalue int8,
        primary key (sequencename)
    );

    alter table app_user 
       add constraint UK_402w3l4kuyco61co58v3ksuys unique (user_info_id_fk);

    alter table app_user 
       add constraint UK_cpt2jpnop7mcpds1sv2i5629w unique (user_name);

    alter table app_user 
       add constraint FKli4km2rxii1m4874b2wm15n6l 
       foreign key (user_info_id_fk) 
       references app_user_info;

    alter table app_user_right 
       add constraint FKbp2p7fxirwlx9uq42uny6ddt9 
       foreign key (user_id_fk) 
       references app_user;


insert into bt_sequences (sequencename, sequencevalue)  values ('app_user',10);
insert into bt_sequences (sequencename, sequencevalue)  values ('app_user_info',10);
insert into bt_sequences (sequencename, sequencevalue)  values ('app_user_right',10);


