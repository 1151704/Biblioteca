/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.modelo;

import ufps.util.colecciones_seed.Pila;

/**
 *
 * @author OMAR MONTES
 */
public class Facultad {

    private byte facultad;

    private Pila<Libro> libros;

    public Facultad() {
    }

    public Facultad(byte facultad) {
        this.facultad = facultad;
        this.libros = new Pila<>();
    }

    public byte getFacultad() {
        return facultad;
    }

    public void setFacultad(byte facultad) {
        this.facultad = facultad;
    }

    public Pila<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Pila<Libro> libros) {
        this.libros = libros;
    }

    public void insertarLibro(Libro libro) {
        this.libros.apilar(libro);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.facultad;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Facultad other = (Facultad) obj;
        if (this.facultad != other.facultad) {
            return false;
        }
        return true;
    }

}
