drop procedure if exists processar_fechamento_mes;

delimiter //

create procedure processar_fechamento_mes(in mes_selecionado_p varchar(10), in valor_saldo_mensal_p double)
begin
    --
    declare done int default false;
    declare vn_valor_resultante double;
    declare vn_valor_total_divida double;
    declare vn_valor_temp double;
    declare vn_id_calculo_mensal int;
    --
    declare dividas cursor for
        select d.valor
          from divida d
         where d.mes_divida = upper(mes_selecionado_p);
    declare continue handler for not found  set done = true;
    --
    set vn_valor_total_divida = 0;
    set vn_valor_resultante = 0;
    --
    -- Somando todos os valores das dividas --
    open dividas;
    --
    start_loop: loop
        --
        fetch dividas into vn_valor_temp;
        if done then
            --
            leave start_loop;
            --
        end if;
        --
        set vn_valor_total_divida = vn_valor_total_divida + vn_valor_temp;
        --
    end loop;
    --
    close dividas;
    --
    set vn_valor_resultante = valor_saldo_mensal_p - vn_valor_total_divida;
    --
    insert into calculo_mensal(mes, valor_saldo_mensal, valor_resultante, valor_total_divida)
    values(upper(mes_selecionado_p), valor_saldo_mensal_p, format(vn_valor_resultante, 2), vn_valor_total_divida);
    --
    -- Recuperando o id gerado do fechamento mensal --
    select last_insert_id() into vn_id_calculo_mensal;
    --
    update divida d set d.calculo_mensal_id = vn_id_calculo_mensal
    where d.mes_divida = upper(mes_selecionado_p);
    --
    commit;
    --
end;
//

delimiter ;