package com.example.utils;

import com.example.dao.ClienteDAO;
import com.example.dto.ClienteDTO;
import com.example.entities.Cliente;
import com.example.entities.Factura;
import com.example.entities.FacturaProducto;
import com.example.entities.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class HelperMySQL {
    private Connection conn = null;

    public HelperMySQL () {
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/integrador1";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                 | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            conn = DriverManager.getConnection(uri, "root", "");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void dropTables() throws SQLException {
        String dropFacturasProductos = "DROP TABLE IF EXISTS factura_producto";
        this.conn.prepareStatement(dropFacturasProductos).execute();
        this.conn.commit();

        String dropFacturas = "DROP TABLE IF EXISTS factura";
        this.conn.prepareStatement(dropFacturas).execute();
        this.conn.commit();

        String dropProductos = "DROP TABLE IF EXISTS producto";
        this.conn.prepareStatement(dropProductos).execute();
        this.conn.commit();

        String dropClientes = "DROP TABLE IF EXISTS cliente";
        this.conn.prepareStatement(dropClientes).execute();
        this.conn.commit();

    }

    public void createTables() throws SQLException {
        String tableCliente = "CREATE TABLE IF NOT EXISTS Cliente(" +
                "idCliente INT NOT NULL, " +
                "nombre VARCHAR(50), " +
                "email VARCHAR(100), " +
                "CONSTRAINT Cliente_pk PRIMARY KEY (idCliente));" ;
        this.conn.prepareStatement(tableCliente).execute();
        this.conn.commit();
        String tableFactura = "CREATE TABLE IF NOT EXISTS Factura(" +
                "idFactura INT NOT NULL, " +
                "idCliente INT NOT NULL, " +
                "CONSTRAINT Factura_pk PRIMARY KEY (idFactura), "+
                "CONSTRAINT FK_idCliente FOREIGN KEY (idCliente) REFERENCES Cliente (idCliente));";
        this.conn.prepareStatement(tableFactura).execute();
        this.conn.commit();
        String tableProducto = "CREATE TABLE IF NOT EXISTS Producto (" +
                "idProducto INT NOT NULL, " +
                "nombre VARCHAR(50), " +
                "`valor` FLOAT, " +
                "CONSTRAINT Producto_pk PRIMARY KEY (idProducto));";
        this.conn.prepareStatement(tableProducto).execute();
        this.conn.commit();
        String tableFacturaProducto = "CREATE TABLE IF NOT EXISTS Factura_Producto (" +
                "idFactura INT NOT NULL, " +
                "idProducto INT NOT NULL, " +
                "cantidad INT, " +
                "CONSTRAINT FK_idFactura FOREIGN KEY (idFactura) REFERENCES Factura (idFactura), " +
                "CONSTRAINT FK_idProducto FOREIGN KEY (idProducto) REFERENCES Producto (idProducto)" +
                ");";
        this.conn.prepareStatement(tableFacturaProducto).execute();
        this.conn.commit();
    }

    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = "src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.withHeader(header).parse(in);

        Iterable<CSVRecord> records = csvParser.getRecords();
        return records;
    }

    public void populateDB() throws Exception {
        try {
            for(CSVRecord row : getData("clientes.csv")) {
                if(row.size() >= 3) {
                    String idString = row.get(0);
                    String nombreString = row.get(1);
                    String emailString = row.get(2);
                    if(!idString.isEmpty()) {
                        try {
                            int id = Integer.parseInt(idString);
                            Cliente cliente = new Cliente(id, nombreString, emailString);
                            insertCliente(cliente, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de clientes: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║    ✅ Clientes insertados    ║");
            System.out.println("╚══════════════════════════════╝");

            for (CSVRecord row : getData("facturas.csv")) {
                if (row.size() >= 2) {
                    String idFacturaString = row.get(0);
                    String idClienteString = row.get(1);

                    if (!idFacturaString.isEmpty() && !idClienteString.isEmpty()) {
                        try {
                            int idFactura = Integer.parseInt(idFacturaString);
                            int idCliente = Integer.parseInt(idClienteString);

                            Factura factura = new Factura(idFactura, idCliente);
                            insertFactura(factura, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de Factura: " + e.getMessage());
                        }
                    }
                }
            }

            System.out.println("╔══════════════════════════════╗");
            System.out.println("║    📄 Facturas insertadas    ║");
            System.out.println("╚══════════════════════════════╝");

            for (CSVRecord row : getData("productos.csv")) {
                if (row.size() >= 3) {
                    String idProductoString = row.get(0);
                    String nombre = row.get(1);
                    String valorString = row.get(2);

                    if (!idProductoString.isEmpty()) {
                        try {
                            int idProducto = Integer.parseInt(idProductoString);
                            float valor = Float.parseFloat(valorString);

                            Producto producto = new Producto(idProducto, nombre, valor);
                            insertProducto(producto, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de Producto: " + e.getMessage());
                        }
                    }
                }
            }

            System.out.println("╔══════════════════════════════╗");
            System.out.println("║    📦 Productos insertados   ║");
            System.out.println("╚══════════════════════════════╝");


            for (CSVRecord row : getData("facturas-productos.csv")) {
                if (row.size() >= 3) {
                    String idFacturaString = row.get(0);
                    String idProductoString = row.get(1);
                    String cantidadString = row.get(2);

                    if (!idFacturaString.isEmpty() && !idProductoString.isEmpty()) {
                        try {
                            int idFactura = Integer.parseInt(idFacturaString);
                            int idProducto = Integer.parseInt(idProductoString);
                            int cantidad = Integer.parseInt(cantidadString);

                            FacturaProducto facturaProducto = new FacturaProducto(idFactura, idProducto, cantidad);
                            insertFacturaProducto(facturaProducto, conn);
                        } catch (NumberFormatException e) {
                            System.err.println("Error de formato en datos de Factura_Producto: " + e.getMessage());
                        }
                    }
                }
            }

            System.out.println("╔════════════════════════════════════╗");
            System.out.println("║ ✅ Relación Factura_Producto OK!   ║");
            System.out.println("╚════════════════════════════════════╝");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int insertCliente (Cliente cliente, Connection conn) throws Exception{
        String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1,cliente.getId());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }


    private int insertFactura(Factura factura, Connection conn) throws Exception {

        String insert = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1,factura.getId());
            ps.setInt(2, factura.getIdCliente());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }

    private int insertProducto(Producto producto, Connection conn) throws Exception {

        String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, producto.getId());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getValor());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }

    private int insertFacturaProducto(FacturaProducto facturaProducto, Connection conn) throws Exception {

        String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(insert);
            ps.setInt(1, facturaProducto.getIdFactura());
            ps.setInt(2,facturaProducto.getIdProducto());
            ps.setInt(3, facturaProducto.getCantidad());
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePsAndCommit(conn, ps);
        }
        return 0;
    }


    private void closePsAndCommit(Connection conn, PreparedStatement ps) {
        if (conn != null){
            try {
                ps.close();
                conn.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
