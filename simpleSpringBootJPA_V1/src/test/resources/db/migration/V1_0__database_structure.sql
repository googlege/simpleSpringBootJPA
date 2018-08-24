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
        userEntity_id int8 not null,
        primary key (id)
    );

    create table bt_sequences (
       sequencename varchar(255) not null,
        sequencevalue int8,
        primary key (sequencename)
    );

    alter table app_user 
       add constraint UK_4oi2tp09ejucxa18xa4aharvm unique (userInfo_id);

    alter table app_user 
       add constraint UK_cpt2jpnop7mcpds1sv2i5629w unique (user_name);

    alter table app_user 
       add constraint FKaixljxm1pv5en4vvj7qp1xu1i 
       foreign key (userInfo_id) 
       references app_user_info;

    alter table app_user_right 
       add constraint FK4w58capge2iq6nu47inityb6u 
       foreign key (userEntity_id) 
       references app_user;


insert into bt_sequences (sequencename, sequencevalue)  values ('app_user',10);
insert into bt_sequences (sequencename, sequencevalue)  values ('app_user_info',10);
insert into bt_sequences (sequencename, sequencevalue)  values ('app_user_right',10);


