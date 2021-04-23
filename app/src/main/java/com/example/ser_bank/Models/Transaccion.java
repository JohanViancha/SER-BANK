package com.example.ser_bank.Models;

public class Transaccion {

    private int id;
    private String tipo;
    private double monto;
    private int id_cue_emi;
    private String nombre_emi;
    private String nombre_rec;
    private int id_cue_rec;
    private String fecha;


    public Transaccion(String tipo, double monto, int id_cue_emi, int id_cue_rec, String fecha) {
        this.tipo = tipo;
        this.monto = monto;
        this.id_cue_emi = id_cue_emi;
        this.id_cue_rec = id_cue_rec;
        this.fecha = fecha;
    }

    public Transaccion(String tipo, double monto, String nombre_emi, String nombre_rec, String fecha) {
        this.tipo = tipo;
        this.monto = monto;
        this.nombre_emi = nombre_emi;
        this.nombre_rec = nombre_rec;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getId_cue_emi() {
        return id_cue_emi;
    }

    public void setId_cue_emi(int id_cue_emi) {
        this.id_cue_emi = id_cue_emi;
    }

    public int getId_cue_rec() {
        return id_cue_rec;
    }

    public void setId_cue_rec(int id_cue_rec) {
        this.id_cue_rec = id_cue_rec;
    }

    public String getNombre_emi() {
        return nombre_emi;
    }

    public void setNombre_emi(String nombre_emi) {
        this.nombre_emi = nombre_emi;
    }

    public String getNombre_rec() {
        return nombre_rec;
    }

    public void setNombre_rec(String nombre_rec) {
        this.nombre_rec = nombre_rec;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
