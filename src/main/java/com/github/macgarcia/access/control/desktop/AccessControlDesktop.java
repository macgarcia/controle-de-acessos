package com.github.macgarcia.access.control.desktop;

import com.github.macgarcia.access.control.desktop.repository.NotaRepository;

/**
 *
 * @author macgarcia
 */
public class AccessControlDesktop {

    public static void main(String[] args) {
        System.out.println("-- Start -- ");
        NotaRepository dao = new NotaRepository();
        /*
        
        Nota notaComHistorico = dao.getNotaComHistorico(2);
        Nota nota = dao.consultarPorId(Nota.class, 2);
        System.out.println(nota);
        System.out.println("----------------------");
        System.out.println(notaComHistorico.comHistorico());
        System.out.println("----------------------");
*/
        System.out.println(dao.getTodasNotas());
    }
}
