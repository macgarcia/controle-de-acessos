drop procedure if exists gerar_json;

delimiter //

create procedure gerar_json(in id int, in categoria varchar(255), in data_divida date,
                            in descricao varchar(45), in mes_divida varchar(255),
                            in valor double, in calculo_mensal_id bigint, in ano int)
begin
    --
    declare vv_json_divida varchar(500);
    declare vn_count int;
    --
    select json_object( "id", id, 
                        "categoria", categoria,
                        "dataDivida", data_divida,
                        "descricao", descricao,
                        "mesDivida", mes_divida,
                        "valor", valor,
                        "calculoMensalId", calculo_mensal_id,
                        "ano", ano
                        )
        into vv_json_divida;
    --
    select count(*)
      into vn_count
      from divida_json_backup a
     where a.divida_id = id;
    --
    if vn_count = 1 then
        --
        update divida_json_backup a
          set a.divida_json = vv_json_divida
         where a.divida_id = id; 
        --
    else
        --
        insert into divida_json_backup(divida_json, divida_id)
        values(vv_json_divida, id);
        --
    end if;
    --
end;
//

delimiter ;

--

drop trigger if exists gerar_json_divida_insert;

delimiter //

create trigger gerar_json_divida_insert after insert on divida
for each row
begin
    --
    call gerar_json(
        new.id,
        new.categoria,
        new.data_divida,
        new.descricao,
        new.mes_divida,
        new.valor,
        new.calculo_mensal_id,
        new.ano
        );
    --
end;
//

delimiter ;

--

drop trigger if exists gerar_json_divida_update;

delimiter //

create trigger gerar_json_divida_update before update on divida
for each row
begin
    --
    call gerar_json(
        old.id,
        new.categoria,
        new.data_divida,
        new.descricao,
        new.mes_divida,
        new.valor,
        new.calculo_mensal_id,
        new.ano
        );
    --
end;
//

delimiter ;
