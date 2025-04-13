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
        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                   Grupo N° 7: Bascuñan Jazmín, Fernández Mateo                     ║");
        System.out.println("║════════════════════════════════════════════════════════════════════════════════════║");
        System.out.println("║  ░█▀█░█▀▄░█▀█░█░█░█▀▀░█▀▀░▀█▀░█▀█░░░▀█▀░█▀█░▀█▀░█▀▀░█▀▀░█▀▄░█▀█░█▀▄░█▀█░█▀▄░░░▀█░  ║");
        System.out.println("║  ░█▀▀░█▀▄░█░█░░█░░█▀▀░█░░░░█░░█░█░░░░█░░█░█░░█░░█▀▀░█░█░█▀▄░█▀█░█░█░█░█░█▀▄░░░░█░  ║");
        System.out.println("║  ░▀░░░▀░▀░▀▀▀░░▀░░▀▀▀░▀▀▀░░▀░░▀▀▀░░░▀▀▀░▀░▀░░▀░░▀▀▀░▀▀▀░▀░▀░▀░▀░▀▀░░▀▀▀░▀░▀░░░▀▀▀  ║");
        System.out.println("║════════════════════════════════════════════════════════════════════════════════════║");
        System.out.println("║                        Carga y Consulta de Datos con JDBC                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════╝\n");

        HelperMySQL dbMySQL = new HelperMySQL();
        dbMySQL.dropTables();
        dbMySQL.createTables();
        dbMySQL.populateDB();
        dbMySQL.closeConnection();


        System.out.println(formatSeparator());
        AbstractFactory chosenFactory = AbstractFactory.getDAOFactory(1);
        System.out.println(formatTitle("Producto con mayor recaudación:"));
        ProductoDAO productoDAO = chosenFactory.getProductoDAO();

        ProductoDTO productoDTO = productoDAO.getProductoConMayorRecaudacion();
        System.out.println(productoDTO);

        System.out.println(formatSeparator());
        System.out.println(formatTitle("Clientes ordenados por facturación:"));
        ClienteDAO clienteDAO = chosenFactory.getClienteDAO();

        List<ClienteDTO> lista = clienteDAO.getClientesOrdenados();
        //System.out.println(lista);
        for (ClienteDTO cliente : lista) {
            System.out.println(cliente);
        }
    }
    /**
     * Genera un título estético con bordes.
     *
     * @param title El texto del título.
     * @return Un String formateado con borde superior e inferior.
     */
    public static String formatTitle(String title) {
        int totalWidth = 50;  // Ancho total del recuadro
        // Crear borde: restamos 2 para considerar las esquinas.
        String border = "═".repeat(totalWidth - 2);
        // Calcular relleno para centrar el título
        int pad = (totalWidth - title.length() - 2) / 2;
        String formattedTitle = "║" + " ".repeat(pad) + title +
                " ".repeat(totalWidth - title.length() - 2 - pad) + "║";

        // Se arma el recuadro completo con salto de línea al inicio y fin
        return
                "╔" + border + "╗\n" +
                formattedTitle + "\n" +
                "╚" + border + "╝";
    }
    public static String formatSeparator() {
        return "\n" + "═".repeat(90) + "\n";

    }

}