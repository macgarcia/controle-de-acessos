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

