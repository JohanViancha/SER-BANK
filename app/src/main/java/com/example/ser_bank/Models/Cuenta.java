package com.example.ser_bank.Models;

public class Cuenta {

    private int id;
    private String codigo;
    private double saldo;
    private String tipo;


    public Cuenta(double saldo, String tipo) {
        this.saldo = saldo;
        this.tipo = tipo;
    }

    public Cuenta(int id, String codigo, double saldo, String tipo) {
        this.id = id;
        this.codigo = codigo;
        this.saldo = saldo;
        this.tipo = tipo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
