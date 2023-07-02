create table nota ( id number
       	     	  , id_integracao number not null
       	     	  , descricao varchar2(50) null
       	     	  , titulo varchar2(50) not null
       	     	  , data_criacao date not null
       	     	  , usuario varchar2(50) not null
       	     	  , senha varchar2(50) not null
       	     	  , url_site varchar2(200) null
       	     	  , data_atualizacao date null
       	     	  , constraint pk_nota primary key(id)
       	     	  );

comment on table nota is 'Tabela de anota��es';
comment on column nota.id is 'Identificador da tabela';
comment on column nota.id_integracao is 'Id de integra��o da nota';
comment on column nota.descricao is 'Descri��o da anota��o';
comment on column nota.titulo is 'Titulo da anota��o';
comment on column nota.data_criacao is 'Data e hora da cria��o da anota��o';
comment on column nota.usuario is 'Nome do usu�rio ou login de uso';
comment on column nota.senha is 'Senha de acesso';
comment on column nota.url_site is 'Site do acesso da anota��o';
comment on column nota.data_atualizacao is 'Data e hora da atualiza��o da anota��o';

create sequence nota_sequence
  start with 1
  increment by 1
  nocache
  nocycle;
  
alter table nota
   add constraint uk_integracao unique(id_integracao);

--

create table historico_nota ( id number not null
       	     		    , id_nota number not null
       	     		    , data_validade_inicial date not null
       	     		    , data_validade_final date not null
       	     		    , numero_atualizacao number not null
	       	     	    , descricao varchar2(50) null
	       	     	    , titulo varchar2(50) not null
	       	     	    , data_criacao date not null
	       	     	    , usuario varchar2(50) not null
	       	     	    , senha varchar2(50) not null
	       	     	    , url_site varchar2(200) null
	       	     	    , constraint pk_historico_nota primary key (id)
			    );

comment on table historico_nota is 'Tabela que armazena historico das anota��es';
comment on column historico_nota.id is 'Identificador da tabela';
comment on column historico_nota.id_nota is 'Id relacionado a tabela nota';
comment on column historico_nota.data_validade_inicial is 'Data de validade inicial da anota��o';
comment on column historico_nota.data_validade_final is 'Data de validade final da anota��o';
comment on column historico_nota.numero_atualizacao is 'Ordem das atualiza��es realizadas';
comment on column historico_nota.descricao is 'Descri��o da anota��o';
comment on column historico_nota.titulo is 'Titulo da anota��o';
comment on column historico_nota.data_criacao is 'Data e hora da cria��o da anota��o';
comment on column historico_nota.usuario is 'Nome do usu�rio ou login de uso';
comment on column historico_nota.senha is 'Senha de acesso';
comment on column historico_nota.url_site is 'Site do acesso da anota��o';

create sequence historico_nota_sequence
  start with 1
  increment by 1
  nocache
  nocycle;

alter table historico_nota
   add constraint fk_historico_para_nota foreign key(id_nota)
   references nota(id);

create index idx_historico_nota on historico_nota(id_nota);

create table log_nota ( acao varchar2(10)
       	     	      , data_hora_acao date
       	     	      , id_nota number
		      );

comment on table log_nota is 'Log de altera��es da nota';
comment on column log_nota.acao is 'A��o de a�tera��o. INSERT, UPDATE, DELETE';
comment on column log_nota.data_hora_acao is 'Momento em que houve a movimenta��o de dados';
comment on column log_nota.id_nota is 'Identificador da nota que sofreu a a��o';
