package com.example.dao;

import com.example.dto.ProductoDTO;
import com.example.entities.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {
    private Connection conn;

    public ProductoDAO(Connection conn) {
        this.conn = conn;
    }

    public ProductoDTO getProductoConMayorRecaudacion() {
        String query = "SELECT p.idProducto, p.nombre, p.valor, fp.cantidad " +
                "FROM Producto p " +
                "JOIN Factura_Producto fp on p.idProducto = fp.idProducto;";
        ProductoDTO result = null;

        Double recaudacionMax = 0.0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()) {
                int idProducto = rs.getInt("idProducto");
                String nombre = rs.getString("nombre");
                float valor = rs.getFloat("valor");
                int cantidad = rs.getInt("cantidad");

                Double recaudacion = (double) (valor * cantidad);
                ProductoDTO producto = new ProductoDTO(nombre, valor, recaudacion);

                if(recaudacion > recaudacionMax) {
                    recaudacionMax = recaudacion;
                    result = producto;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
