/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.negocio;

import ufps.modelo.Facultad;
import ufps.modelo.Libro;
import ufps.modelo.Mensaje;
import ufps.modelo.Solicitud;
import ufps.util.ArchivoLeerURL;
import ufps.util.colecciones_seed.ColaP;
import ufps.util.colecciones_seed.ListaCD;
import ufps.util.colecciones_seed.Pila;
import ufps.util.colecciones_seed.Secuencia;

/**
 *
 * @author OMAR MONTES
 */
public class Biblioteca {

    private ColaP<Solicitud> solicitudes;

    private Secuencia<Facultad> facultades;

    private ListaCD<Mensaje> mensajes;

    private final String urlSolicitudes = "http://madarme.megaterios.co/solicitudes.txt";
    private final String urlInventarios = "http://madarme.megaterios.co/inventario.txt";

    public Biblioteca() {

        this.solicitudes = new ColaP<>();
        this.facultades = new Secuencia<>(6);
        this.mensajes = new ListaCD<>();

        this.crearFacultades();

        this.cargarInventario();

        this.cargarSolicitudes();

        this.crearMensajes();

    }

    private void crearFacultades() {

        for (int i = 1; i <= this.facultades.getCapacidad(); i++) {
            this.facultades.insertar(new Facultad((byte) i));
        }

    }

    private void cargarInventario() {

        ArchivoLeerURL archivoLeer = new ArchivoLeerURL(urlInventarios);

        Object[] lineas = archivoLeer.leerArchivo();

        String[] datos;

        long libro_codigo;

        Facultad facultad;

        for (int j = 1; j < lineas.length; j++) {
            
            datos = lineas[j].toString().split(";");

            libro_codigo = Long.parseLong(datos[0]);

            facultad = this.getFacultadLibro(libro_codigo);

            if (facultad != null) {

                facultad.getLibros().apilar(new Libro(libro_codigo, datos[1]));

            }

        }

    }

    private void cargarSolicitudes() {

        ArchivoLeerURL archivoLeer = new ArchivoLeerURL(urlSolicitudes);

        Object[] lineas = archivoLeer.leerArchivo();

        String[] datos;

        byte tipoUsuario;

        for (int i = 1; i < lineas.length; i++) {

            datos = lineas[i].toString().split(";");

            tipoUsuario = Byte.parseByte(datos[2]);

            this.solicitudes.enColar(new Solicitud(tipoUsuario, Byte.parseByte(datos[1]), Long.parseLong(datos[0])), tipoUsuario);

        }

    }

    private void crearMensajes() {
        Solicitud solicitud;

        ArchivoLeerURL archivoLeer = new ArchivoLeerURL(urlInventarios);

        Object[] lineas = archivoLeer.leerArchivo();

        Facultad facultad;

        while (!this.solicitudes.esVacia()) {

            solicitud = this.solicitudes.deColar();

            facultad = this.getFacultadLibro(solicitud.getCod_libro());

            if (facultad != null) {

                if (solicitud.getTipo_solicitud() == 1) {
                    // RESERVA
                    if (this.reservarLibro(facultad, solicitud.getCod_libro())) {
                        this.mensajes.insertarAlFinal(new Mensaje("Se reservó el libro con código: " + solicitud.getCod_libro() + " " + (solicitud.getId_usuario() == 1 ? "Estudiante" : "Profesor")));
                    } else {
                        this.mensajes.insertarAlFinal(new Mensaje("No se reservó el libro con código: " + solicitud.getCod_libro() + " no se encontraba en el inventario"));
                    }
                }
                if (solicitud.getTipo_solicitud() == 2) {
                    // ENTREGA
                    this.devolverLibro(facultad, solicitud.getCod_libro(), lineas);
                    this.mensajes.insertarAlFinal(new Mensaje("Se entregó el libro con código: " + solicitud.getCod_libro() + " " + (solicitud.getId_usuario() == 1 ? "Estudiante" : "Profesor")));
                }

            } else {
                this.mensajes.insertarAlFinal(new Mensaje("El código: " + solicitud.getCod_libro() + " es inválido"));
            }
        }
    }

    private Facultad getFacultadLibro(long codigoLibro) {

        if (String.valueOf(codigoLibro).length() == 6) {

            byte cod_facultad = (byte) ((codigoLibro / 100000) - 1);

            if (cod_facultad >= 0 && cod_facultad < facultades.getTamanio()) {
                return this.facultades.get(cod_facultad);
            }
        }

        return null;
    }

    private boolean reservarLibro(Facultad facultad, long codigo) {

        Pila<Libro> tmp = new Pila<>();

        boolean reservo = false;

        Libro l;

        while (!facultad.getLibros().esVacia()) {

            l = facultad.getLibros().desapilar();

            if (l.getCod_libro() == codigo) {
                reservo = true;
            } else {
                tmp.apilar(l);
            }

        }

        facultad.setLibros(tmp);

        return reservo;
    }

    private void devolverLibro(Facultad facultad, long codigo, Object[] lineas) {
        String[] datos;

        long libro_codigo;

        for (int j = 1; j < lineas.length; j++) {

            datos = lineas[j].toString().split(";");

            libro_codigo = Long.parseLong(datos[0]);

            if (libro_codigo == codigo) {

                facultad.getLibros().apilar(new Libro(libro_codigo, datos[1]));
                return;

            }

        }
    }

    public ListaCD<Mensaje> getMensajes() {
        return mensajes;
    }

}
