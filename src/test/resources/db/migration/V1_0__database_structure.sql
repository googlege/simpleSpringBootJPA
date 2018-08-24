   create table app_user (
       ID int8 not null,
        USER_PASSWORD varchar(10) not null,
        USER_NAME varchar(10) not null,
        VERSION int4 not null,
        USERINFO_ID_FK int8 not null,
        primary key (ID)
    );

    create table app_user_info (
       ID int8 not null,
        email varchar(100),
        name varchar(60),
        VERSION int4 not null,
        vorname varchar(60),
        primary key (ID)
    );

    create table app_user_right (
       ID int8 not null,
        USER_RIGHT int4 not null,
        VERSION int4 not null,
        USER_ID_FK int8 not null,
        primary key (ID)
    );

    create table bt_sequences (
       SEQUENCENAME varchar(255) not null,
        SEQUENCEVALUE int8,
        primary key (SEQUENCENAME)
    );

    alter table app_user 
       add constraint UK_6ue9yqpj69xc953p73b425l9f unique (USERINFO_ID_FK);

    alter table app_user 
       add constraint UK_qovlxo5j21ftfmv6yqtpwnx2 unique (USER_NAME);

    alter table app_user 
       add constraint FKi1b3dka5r78vbko3ahtndrtqw 
       foreign key (USERINFO_ID_FK) 
       references app_user_info;

    alter table app_user_right 
       add constraint FK390piq9hwhw7rnie44unjp30i 
       foreign key (USER_ID_FK) 
       references app_user;



insert into bt_sequences (SEQUENCENAME, SEQUENCEVALUE)  values ('app_user',10);
insert into bt_sequences (SEQUENCENAME, SEQUENCEVALUE)  values ('app_user_info',10);
insert into bt_sequences (SEQUENCENAME, SEQUENCEVALUE)  values ('app_user_right',10);


