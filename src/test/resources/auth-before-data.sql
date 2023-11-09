drop table if exists admins cascade;

    create table admins (
        id bigserial not null,
        admin_login varchar(255) not null,
        role varchar(255) not null,
        password varchar(255) not null,
        primary key (id)
    );

insert into admins (admin_login, role, password) values ('test@mail.com', 'ADMIN', 'MSFnNmswZjQ=');