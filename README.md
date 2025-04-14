# 🏢 Proyecto de Gestión de Facturación con JDBC

Este proyecto es una aplicación en Java que utiliza JDBC para interactuar con una base de datos MySQL. Gestiona entidades principales como **Clientes**, **Facturas**, **Productos** y sus relaciones en **FacturaProducto**.

---

## 🧑‍💻 Grupo de Trabajo
Este proyecto fue desarrollado por el **Grupo N° 7**

---

## 🤝 Integrantes del Grupo
- **Bascuñan Jazmín**
- **Fernández Mateo**

---

## ✅ Requisitos

1. **Java**: JDK 11 o superior.
2. **MySQL**: Una instancia de base de datos MySQL en ejecución.
3. **JDBC Driver**: `com.mysql.cj.jdbc.Driver` incluido como dependencia.
4. **Librerías**:
    - `commons-csv` para procesar los archivos **CSV** (ya incluido en el proyecto).
---

## 📂 Estructura del Proyecto

### 1. **📌 Código Principal**
- **`Main.java`**:
    - Punto de entrada del programa.
    - Inicializa la base de datos, llena las tablas con datos desde CSV y realiza consultas como:
        - Producto con mayor recaudación.
        - Clientes ordenados por facturación.

### 2. **⚙️ Paquetes Principales**
- **`utils`**:
    - Gestor de la conexión con la base de datos y operaciones sobre tablas.
- **`entities`**:
    - Clases que representan las entidades de la base de datos: `Cliente`, `Factura`, `Producto`, `FacturaProducto`.
- **`daos`**:
    - Implementación del patrón DAO con fábricas para manejar operaciones en las distintas tablas.

### 3. **📜 Archivos CSV**
- `clientes.csv`, `facturas.csv`, `productos.csv`, `factura-productos.csv`:
    - Datos iniciales para rellenar las tablas de la base de datos.

---

## ⚙️ Ejecución del Proyecto

### Configuración Inicial
1. Asegúrate de que tu servidor MySQL esté corriendo en `localhost`.
2. Crea una base de datos llamada `integrador1`:
    ```sql
    CREATE DATABASE integrador1;
    ```
3. Actualiza las credenciales de conexión en el código:
    - Usuario: `root`
    - Contraseña: *(cadena vacía)*.

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

## 📖 Funcionalidades Principales
- Gestión de clientes, productos y facturas en base de datos.
- Consultas avanzadas, como:
    - Producto con mayores ventas.
    - Clientes ordenados por volumen de facturación.
- Manejo de datos iniciales desde archivos CSV.
- Creación dinámica de tablas y relaciones en MySQL.

---