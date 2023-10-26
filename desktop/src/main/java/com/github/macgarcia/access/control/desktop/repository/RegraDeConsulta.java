package com.github.macgarcia.access.control.desktop.repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author macgarcia
 * @param <T>
 */
public interface RegraDeConsulta<T> {
    
    T selecionarRegistros();
    T where();
    T and();
    T or();
    T parametros(Map<String, Object> parametros);
    List<?> executarConsulta();
    
}
