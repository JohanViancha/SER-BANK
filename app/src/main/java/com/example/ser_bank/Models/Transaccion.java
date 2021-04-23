package com.example.ser_bank.Models;

public class Transaccion {

    private int id;
    private String tipo;
    private double monto;

    public Transaccion(int id, String tipo, double monto) {
        this.id = id;
        this.tipo = tipo;
        this.monto = monto;
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
}
