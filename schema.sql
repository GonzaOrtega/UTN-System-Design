
    create table Guardarropa (
        id bigint generated by default as identity (start with 1),
        primary key (id)
    )

    create table Guardarropa_Prenda (
        Guardarropa_id bigint not null,
        prendas_id bigint not null,
        primary key (Guardarropa_id, prendas_id)
    )

    create table Prenda (
        id bigint generated by default as identity (start with 1),
        colorPrimario varchar(255),
        colorSecundario varchar(255),
        nivelAbrigo integer not null,
        tela varchar(255),
        tipo varchar(255),
        usada boolean not null,
        primary key (id)
    )

    alter table Guardarropa_Prenda 
        add constraint UK_8n0axiim561iy41oiei524vwl  unique (prendas_id)

    alter table Guardarropa_Prenda 
        add constraint FK_8n0axiim561iy41oiei524vwl 
        foreign key (prendas_id) 
        references Prenda

    alter table Guardarropa_Prenda 
        add constraint FK_j0c4op9om71xmsy78yoixghjv 
        foreign key (Guardarropa_id) 
        references Guardarropa
