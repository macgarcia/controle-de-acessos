create or replace trigger insere_historico before update on nota
referencing old as old new as new
for each row
when (new.data_atualizacao is not null)
declare
   --
   novo_historico HistoricoNotaType;
   log_nota 	  LogNotaType;
   data_validade  date;
   contador	  number;
   --
begin
   --
   select case 
             when :old.data_atualizacao is null then :old.data_criacao
   	     when :old.data_atualizacao is not null then :old.data_atualizacao
	  end as periodo
     into data_validade
     from dual;

   select count(*) + 1
     into contador
     from historico_nota
    where id_nota = :new.id;

   novo_historico := HistoricoNotaType(null, :old.id, data_validade,
        :new.data_atualizacao, contador, :old.descricao, :old.titulo,
        :old.data_criacao, :old.usuario, :old.senha, :old.url_site);

   novo_historico.persistir();
   
   log_nota := LogNotaType(2, null, sysdate, :old.id);
   log_nota.persistir();
   --
end;
