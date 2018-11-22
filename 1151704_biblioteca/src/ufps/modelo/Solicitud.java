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
public class Solicitud {

    private byte id_usuario;

    private byte tipo_solicitud;

    private long cod_libro;

    public Solicitud() {
    }

    public Solicitud(byte id_usuario, byte tipo_solicitud, long cod_libro) {
        this.id_usuario = id_usuario;
        this.tipo_solicitud = tipo_solicitud;
        this.cod_libro = cod_libro;
    }

    public byte getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(byte id_usuario) {
        this.id_usuario = id_usuario;
    }

    public byte getTipo_solicitud() {
        return tipo_solicitud;
    }

    public void setTipo_solicitud(byte tipo_solicitud) {
        this.tipo_solicitud = tipo_solicitud;
    }

    public long getCod_libro() {
        return cod_libro;
    }

    public void setCod_libro(long cod_libro) {
        this.cod_libro = cod_libro;
    }

    @Override
    public String toString() {
        return "Solicitud{" + "id_usuario=" + (id_usuario == 1 ? "Estudiante": "Profesor") + ", tipo_solicitud=" +(tipo_solicitud == 1 ? "Reserva" : "Entrega") + ", cod_libro=" + cod_libro + '}';
    }

}
