create table public.BT_SEQUENCES (
 SEQUENCENAME varchar(255) not null, 
 SEQUENCEVALUE bigint, 
 primary key (SEQUENCENAME)
);

insert INTO public.BT_SEQUENCES(SEQUENCENAME,SEQUENCEVALUE) values ('APP_USER',0);
insert INTO public.BT_SEQUENCES(SEQUENCENAME,SEQUENCEVALUE) values ('APP_USER_INFO',0);
insert INTO public.BT_SEQUENCES(SEQUENCENAME,SEQUENCEVALUE) values ('APP_USER_RIGHT',0);


create table public.app_user (id bigint not null, user_password varchar(10) not null, user_name varchar(10) not null, version integer not null, userinfo_id_fk bigint not null, primary key (id));
create table public.app_user_info (id bigint not null, email varchar(100), name varchar(60), version integer not null, vorname varchar(60), primary key (id));
create table public.app_user_right (id bigint not null, user_right integer not null, version integer not null, user_id_fk bigint not null, primary key (id));

alter table public.app_user add constraint UK_g38ha9l6lc1udvff4gui7t6ft unique (userinfo_id_fk);
alter table public.app_user add constraint UK_cpt2jpnop7mcpds1sv2i5629w unique (user_name);
alter table public.app_user add constraint FKrymx86kdq8ujalgqq58rmwcoc foreign key (userinfo_id_fk) references public.app_user_info;
alter table public.app_user_right add constraint FKbp2p7fxirwlx9uq42uny6ddt9 foreign key (user_id_fk) references public.app_user;