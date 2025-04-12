package com.example.dao;

import com.example.entities.Producto;

import java.sql.Connection;

public class ProductoDAO {
    private Connection conn;

    public ProductoDAO(Connection conn) {
        this.conn = conn;
    }

    // TODO Completar método
    public Producto getProductoConMayorRecaudacion() {
        String query = "SELECT * " +
                "FROM Producto ";
        Producto result = null;

        return result;
    }
}
