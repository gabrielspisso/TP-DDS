
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
        formula varchar(255),
        nombre varchar(255),
        primary key (id)
    )

    alter table Balance_Cuenta 
        add constraint UK_s4k8hbrmyuortcb4agkoephcp  unique (cuentas_id)

    alter table Empresa_Balance 
        add constraint UK_qx63q21fvch7p77dctlrij9ts  unique (balances_id)

    alter table Indicador 
        add constraint UK_9hccvtvr87pangjn6epwd3a3b  unique (nombre)

    alter table Balance_Cuenta 
        add constraint FK_s4k8hbrmyuortcb4agkoephcp 
        foreign key (cuentas_id) 
        references Cuenta (id)

    alter table Balance_Cuenta 
        add constraint FK_1j61esna50pocenicisok0umc 
        foreign key (Balance_id) 
        references Balance (id)

    alter table Empresa_Balance 
        add constraint FK_qx63q21fvch7p77dctlrij9ts 
        foreign key (balances_id) 
        references Balance (id)

    alter table Empresa_Balance 
        add constraint FK_t1r57tgfqwqcndlew4kcd8uy1 
        foreign key (Empresa_id) 
        references Empresa (id)
