drop trigger if exists salvar_historico_nota;

delimiter //

create trigger salvar_historico_nota after update on nota
for each row
begin
   --
   declare contador int;
   declare data_atualizacao_inicial datetime;
   --
   if new.data_atualizacao < old.data_atualizacao then
      --
      signal sqlstate '45000' set message_text = 'Data de atualização é mais velha que a data que já existe. trigger interrompido.';
      --
   end if;
   --
   if new.data_atualizacao is not null or new.data_atualizacao > old.data_atualizacao then
      --
      select count(*) + 1
        into contador
        from historico_nota
       where id_nota = old.id;
      --
      select case when old.data_atualizacao is null then old.data_criacao
                  when old.data_atualizacao is not null then old.data_atualizacao
             end as data
        into data_atualizacao_inicial
        from nota
       where id = old.id;
      --
      insert into historico_nota ( id_nota
	     			 , data_validade_inicial
			      	 , data_validade_final
			       	 , numero_atualizacao
				 , descricao
				 , titulo
				 , data_criacao
	 	  		 , usuario
	    			 , senha
			     	 , url_site
			      	 , data_atualizacao
			       	 )
		       	 values  ( old.id
				 , data_atualizacao_inicial
				 , current_timestamp()
				 , contador
				 , old.descricao
				 , old.titulo
				 , old.data_criacao
				 , old.usuario
				 , old.senha
				 , old.url_site
				 , old.data_atualizacao
				 );
      --
   end if;
   --
end;
//

delimiter ;