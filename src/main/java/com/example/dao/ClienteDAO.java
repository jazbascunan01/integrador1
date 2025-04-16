package com.example.dao;

import com.example.dto.ClienteDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public List<ClienteDTO> getClientesOrdenados() {
        String query =
                "SELECT c.nombre, c.email, SUM(fp.cantidad * p.valor) AS total_facturado " +
                        "FROM Cliente c " +
                        "JOIN Factura f ON c.idCliente = f.idCliente " +
                        "JOIN Factura_Producto fp ON f.idFactura = fp.idFactura " +
                        "JOIN Producto p ON fp.idProducto = p.idProducto " +
                        "GROUP BY c.idCliente " +
                        "ORDER BY total_facturado DESC;";

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ClienteDTO> resultado = new ArrayList<>();
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                double total = rs.getDouble("total_facturado");

                ClienteDTO clienteDTO = new ClienteDTO(nombre,email, total);
                resultado.add(clienteDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }
}
