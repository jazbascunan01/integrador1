package com.example.entities;

public class Factura {
    private int id;
    private int idCliente;

    public Factura() {
    }

    public Factura(int id, int idCliente) {
        this.id = id;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                '}';
    }
}
