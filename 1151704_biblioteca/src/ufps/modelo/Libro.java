/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.modelo;

/**
 *
 * @author OMAR MONTES
 */
public class Libro {

    private long cod_libro;

    private String nombre;

    public Libro() {
    }

    public Libro(long cod_libro, String nombre) {
        this.cod_libro = cod_libro;
        this.nombre = nombre;
    }

    public long getCod_libro() {
        return cod_libro;
    }

    public void setCod_libro(long cod_libro) {
        this.cod_libro = cod_libro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
