drop table if exists nota;

create table nota ( id int auto_increment
	, descricao varchar(50) null
	, titulo varchar(50) not null
	, data_criacao datetime not null
	, usuario varchar(50) not null
	, senha varchar(50) not null
	, url_site varchar(200) null
	, data_atualizacao datetime
	, constraint pk_nota primary key (id)
);

drop table if exists historico_nota;

create table historico_nota ( id int auto_increment
	, id_nota int
	, data_validade_inicial datetime not null
	, data_validade_final datetime not null
	, numero_atualizacao int not null
	, descricao varchar(50) null
	, titulo varchar(50) not null
	, data_criacao datetime not null
	, usuario varchar(50) not null
	, senha varchar(50) not null
	, url_site varchar(200) null
	, data_atualizacao datetime
	, constraint pk_nota primary key (id)
);

alter table historico_nota
   add constraint pk_historico_nota foreign key (id_nota)
   references nota(id);

alter table historico_nota
    drop column data_atualizacao;

alter  table historico_nota
   modify id_nota int not null;
   
alter table nota
   add column flag_integrado int not null -- 1 - não integrado, 0 - integrado

drop table if exists configuracao;

create table configuracao ( id int auto_increment
	, ativar_integracao int -- 1 - Ligado, 0 - desligado
	, intervalo_integracao int
	, constraint pk_configuracao primary key(id)
);
alter table configuracao
   add column inicio_imediato int;
   
alter table configuracao
   modify id int;

/* Registro inicial com a integração desligada */
insert into configuracao(id, ativar_integracao, intervalo_integracao) values(1, 0, 10);