# 🏢 Proyecto de Gestión de Facturación con JDBC

Este proyecto es una aplicación en Java que utiliza JDBC para interactuar con una base de datos MySQL. Gestiona entidades principales como **Clientes**, **Facturas**, **Productos** y sus relaciones en **FacturaProducto**.

---

## Índice
1. [Requisitos](#requisitos)
2. [Estructura del Proyecto](#estructura-del-proyecto)
3. [Ejecución del Proyecto](#ejecución-del-proyecto)
4. [Funcionalidades Principales](#funcionalidades-principales)

---

## Requisitos
- **Java**: JDK 11 o superior.
- **MySQL**: Instancia de base de datos en ejecución.
- **JDBC Driver**: `com.mysql.cj.jdbc.Driver`.
- **Librerías externas**:
    - `commons-csv` para procesar archivos **CSV**.

---

## Estructura del Proyecto

### Código Principal
- **`Main.java`**:
    - Punto de entrada del programa.
    - Inicializa la base de datos, llena las tablas con datos desde CSV y realiza consultas como:
        - Producto con mayor recaudación.
        - Clientes ordenados por facturación.

### Paquetes Principales
- **`utils`**:
    - Gestor de la conexión con la base de datos y operaciones sobre tablas.
- **`entities`**:
    - Clases que representan las entidades de la base de datos: `Cliente`, `Factura`, `Producto`, `FacturaProducto`.
- **`daos`**:
    - Implementación del patrón DAO con fábricas para manejar operaciones en las distintas tablas.

### Archivos CSV
- `clientes.csv`, `facturas.csv`, `productos.csv`, `factura-productos.csv`:
    - Datos iniciales para rellenar las tablas de la base de datos.

---

## Ejecución del Proyecto

### Configuración Inicial
1. Asegúrate de que tu servidor MySQL esté corriendo en `localhost`.
2. Crea una base de datos llamada `integrador1`:
    ```sql
    CREATE DATABASE integrador1;
    ```
3. Actualiza las credenciales de conexión en el código:
    - Usuario: `root`
    - Contraseña: *(cadena vacía o tu contraseña configurada)*.

### Ejecución
1. Compila el proyecto:
    ```bash
    javac -cp .:lib/* com/example/Main.java
    ```
2. Ejecútalo:
    ```bash
    java -cp .:lib/* com.example.Main
    ```

---

## Funcionalidades Principales
- Gestión de clientes, productos y facturas en base de datos.
- Consultas avanzadas, como:
    - Producto con mayores ventas.
    - Clientes ordenados por volumen de facturación.
- Manejo de datos iniciales desde archivos CSV.
- Creación dinámica de tablas y relaciones en MySQL.

---