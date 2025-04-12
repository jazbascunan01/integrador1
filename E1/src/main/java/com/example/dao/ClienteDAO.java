package com.example.dao;

import com.example.entities.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDAO {
    private Connection conn;

    public ClienteDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Cliente> getClientesOrdenados() {
        String query = "SELECT * " +
                "FROM Cliente ";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Cliente> resultado = null;
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

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
