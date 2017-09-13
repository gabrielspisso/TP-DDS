
    create table Balance (
        id bigint not null auto_increment,
        anio integer not null,
        periodo varchar(255),
        primary key (id)
    )

    create table Balance_Cuenta (
        Balance_id bigint not null,
        cuentas_id bigint not null
    )

    create table Calculo (
        DTYPE varchar(31) not null,
        id bigint not null auto_increment,
        total double precision,
        primary key (id)
    )

    create table Condicion (
        DTYPE varchar(31) not null,
        id bigint not null auto_increment,
        nombre varchar(255),
        cantidadDeAños integer,
        valorMinimo double precision,
        criterio_id bigint,
        indicador_id bigint,
        calculo_id bigint,
        primary key (id)
    )

    create table Condicion_Condicion (
        Condicion_id bigint not null,
        listaDeCondiciones_id bigint not null
    )

    create table Cuenta (
        id bigint not null auto_increment,
        nombre varchar(255),
        valor integer not null,
        primary key (id)
    )

    create table Empresa (
        id bigint not null auto_increment,
        nombre varchar(255),
        primary key (id)
    )

    create table Empresa_Balance (
        Empresa_id bigint not null,
        balances_id bigint not null
    )

    create table Indicador (
        id bigint not null auto_increment,
        arbol varchar(255),
        nombre varchar(255),
        primary key (id)
    )

    create table Metodologia (
        id bigint not null auto_increment,
        descripcion varchar(255),
        nombre varchar(255),
        primary key (id)
    )

    create table Metodologia_Condicion (
        Metodologia_id bigint not null,
        Condiciones_id bigint not null
    )

    create table criterioDeAceptacionDeCondicion (
        DTYPE varchar(31) not null,
        id bigint not null auto_increment,
        primary key (id)
    )

    alter table Balance_Cuenta 
        add constraint UK_s4k8hbrmyuortcb4agkoephcp  unique (cuentas_id)

    alter table Empresa_Balance 
        add constraint UK_qx63q21fvch7p77dctlrij9ts  unique (balances_id)

    alter table Balance_Cuenta 
        add constraint FK_s4k8hbrmyuortcb4agkoephcp 
        foreign key (cuentas_id) 
        references Cuenta (id)

    alter table Balance_Cuenta 
        add constraint FK_1j61esna50pocenicisok0umc 
        foreign key (Balance_id) 
        references Balance (id)

    alter table Condicion 
        add constraint FK_179idy49f9v5090qrep5y8ov7 
        foreign key (criterio_id) 
        references criterioDeAceptacionDeCondicion (id)

    alter table Condicion 
        add constraint FK_eysyy3klb44uxywscs62nsg3q 
        foreign key (indicador_id) 
        references Indicador (id)

    alter table Condicion 
        add constraint FK_dyji8s1g1fjy2hd4gqy1ti7ky 
        foreign key (calculo_id) 
        references Calculo (id)

    alter table Condicion_Condicion 
        add constraint FK_9ykfa1p0ftx5uxou7jjnlycys 
        foreign key (listaDeCondiciones_id) 
        references Condicion (id)

    alter table Condicion_Condicion 
        add constraint FK_rhl8cjmy68mfc5mr6isbw6lbg 
        foreign key (Condicion_id) 
        references Condicion (id)

    alter table Empresa_Balance 
        add constraint FK_qx63q21fvch7p77dctlrij9ts 
        foreign key (balances_id) 
        references Balance (id)

    alter table Empresa_Balance 
        add constraint FK_t1r57tgfqwqcndlew4kcd8uy1 
        foreign key (Empresa_id) 
        references Empresa (id)

    alter table Metodologia_Condicion 
        add constraint FK_9uudsxraymsm65shg2ndmya4d 
        foreign key (Condiciones_id) 
        references Condicion (id)

    alter table Metodologia_Condicion 
        add constraint FK_tm1r98qy3tqgiu6rsewj6lsun 
        foreign key (Metodologia_id) 
        references Metodologia (id)
