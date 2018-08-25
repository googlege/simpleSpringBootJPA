
    create table app_user (id bigint not null, user_password varchar(10) not null, user_name varchar(10) not null, version integer not null, user_info_id_fk bigint not null, primary key (id));

    create table app_user_info (id bigint not null, email varchar(100), name varchar(60), version integer not null, vorname varchar(60), primary key (id));

    create table app_user_right (id bigint not null, user_right integer not null, version integer not null, user_id_fk bigint not null, primary key (id));

    create table bt_sequences (sequencename varchar(255) not null, sequencevalue bigint, primary key (sequencename));

    alter table app_user add constraint UK_402w3l4kuyco61co58v3ksuys unique (user_info_id_fk);

    alter table app_user add constraint UK_cpt2jpnop7mcpds1sv2i5629w unique (user_name);

    


insert into bt_sequences (sequencename, sequencevalue)  values ('app_user',10);
insert into bt_sequences (sequencename, sequencevalue)  values ('app_user_info',10);
insert into bt_sequences (sequencename, sequencevalue)  values ('app_user_right',10);


