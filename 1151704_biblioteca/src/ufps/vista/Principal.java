/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.vista;

import ufps.modelo.Mensaje;
import ufps.negocio.Biblioteca;

/**
 *
 * @author OMAR MONTES
 */
public class Principal {

    public static void main(String[] args) {

        Biblioteca b = new Biblioteca();
        
        for (Mensaje mensaje : b.getMensajes()) {
            System.out.println(mensaje);
        }

    }

}
