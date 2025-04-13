package com.example;

import com.example.dao.ClienteDAO;
import com.example.dao.ProductoDAO;
import com.example.dto.ClienteDTO;
import com.example.dto.ProductoDTO;
import com.example.factory.AbstractFactory;
import com.example.utils.HelperMySQL;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        HelperMySQL dbMySQL = new HelperMySQL();
        dbMySQL.dropTables();
        dbMySQL.createTables();
        dbMySQL.populateDB();
        dbMySQL.closeConnection();


        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        System.out.println("Producto con mayor recaudación:");
        ProductoDAO productoDAO = chosenFactory.getProductoDAO();

        ProductoDTO productoDTO = productoDAO.getProductoConMayorRecaudacion();
        System.out.println(productoDTO);

        System.out.println("Clientes ordenados por facturación:");
        ClienteDAO clienteDAO = chosenFactory.getClienteDAO();

        List<ClienteDTO> lista = clienteDAO.getClientesOrdenados();
        System.out.println(lista);
    }
}