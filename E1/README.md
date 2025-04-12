# 🏢 Proyecto de Gestión de Facturación con JDBC

Este proyecto es una aplicación de consola basada en Java que emplea JDBC para interactuar con una base de datos MySQL. Está diseñado para gestionar entidades relacionadas con una tienda, incluyendo **Clientes**, **Facturas**, **Productos** y las relaciones entre ellas, como **FacturaProducto**.

---

## 🤝 Integrantes del Grupo
- **Bascuñan Jazmín**
- **Fernández Mateo**

---

## 📑 Tabla de Contenidos
1. [✅ Requisitos](#-requisitos)
2. [📖 Descripción del Proyecto](#-descripción-del-proyecto)
3. [📂 Estructura del Proyecto](#-estructura-del-proyecto)
4. [⚙️ Ejecución del Proyecto](#️-ejecución-del-proyecto)
5. [📁 Archivos y Datos Relevantes](#-archivos-y-datos-relevantes)
6. [🔍 Descripción de Funcionalidades](#-descripción-de-funcionalidades)

---

## ✅ Requisitos

1. **Java**: JDK 11 o superior.
2. **MySQL**: Una instancia de base de datos MySQL en ejecución.
3. **JDBC Driver**: `com.mysql.cj.jdbc.Driver` incluido como dependencia.
4. **Librerías**:
    - `commons-csv` para procesar los archivos **CSV** (ya incluido en el proyecto).

---

## 📖 Descripción del Proyecto

El sistema gestiona la creación, eliminación y consulta de las tablas siguientes:

### Tablas Principales y Relaciones
1. **Clientes**:
    - Contiene información sobre los clientes (ID, nombre y correo electrónico).
2. **Facturas**:
    - Cada factura pertenece a un cliente (relación `idCliente` como clave foránea a la tabla **Clientes**).
3. **Productos**:
    - Lista de productos disponibles (ID, nombre y precio).
4. **FacturaProducto**:
    - Representa una relación entre las facturas y productos con la cantidad adquirida.

---

## 📂 Estructura del Proyecto

### 1. **📌 Código Principal**
- **`Main.java`**:
    - Contiene el punto de entrada del programa.
    - Realiza tareas como:
        - Inicializar la base de datos.
        - Poblarla con datos desde archivos CSV.
        - Ejecutar consultas mediante DAOs.
    - Muestra información como:
        - Producto con mayor recaudación.
        - Clientes ordenados por facturación.

### 2. **⚙️ Paquete `utils`**
- **`HelperMySQL`**:
    - Responsable de crear y eliminar tablas.
    - Maneja la conexión con la base de datos.
    - Guarda métodos para poblar la base de datos utilizando los archivos **CSV**.

### 3. **📦 Paquete `entities`**
- **`Cliente`**, **`Factura`**, **`Producto`**, **`FacturaProducto`**:
    - Clases que representan las entidades principales de la base de datos.

### 4. **🛠️ Patrón DAO**
- **Fábrica Abstracta**:
    - `AbstractFactory`: Implementa el patrón de fábrica abstracta para gestionar instancias de DAOs.
    - `MySQLDAOFactory`: Crea instancias específicas de DAOs basados en MySQL.

### 5. **📜 Archivos CSV**
- `clientes.csv`, `facturas.csv`, `productos.csv`, `facturas-productos.csv`:
    - Contienen datos iniciales para poblar las tablas.

---

## ⚙️ Ejecución del Proyecto

### Configuración Inicial
1. Asegúrate de que un servidor MySQL esté corriendo en `localhost` con el nombre de la base de datos `integrador1`.
2. Crea manualmente una base de datos vacía en MySQL:
   ```sql
   CREATE DATABASE integrador1;
   ```
3. Actualiza las credenciales en el código:
    - Usuario: `root`.
    - Contraseña: *(cadena vacía en este caso)*.

### Compilación y Ejecución
1. Compila el proyecto:
   ```bash
   javac -cp .:lib/* com/example/Main.java
   ```
2. Ejecuta el programa:
   ```bash
   java -cp .:lib/* com.example.Main
   ```

---

## 📁 Archivos y Datos Relevantes

A continuación, se describen los archivos y su propósito en el proyecto:

### **📄 Archivos CSV**
1. **`clientes.csv`**:
    - Contiene los datos iniciales de los clientes.
    - Columnas: `idCliente`, `nombre`, `email`.
2. **`facturas.csv`**:
    - Relaciona clientes con facturas.
    - Columnas: `idFactura`, `idCliente` (clave foránea).
3. **`productos.csv`**:
    - Lista de productos.
    - Columnas: `idProducto`, `nombre`, `valor` (precio del producto).
4. **`facturas-productos.csv`**:
    - Relación entre facturas y productos.
    - Columnas: `idFactura`, `idProducto`, `cantidad`.

### **🏷️ Clases y Componentes Relevantes**
1. **`ClienteDAO`, `FacturaDAO`, `ProductoDAO`**:
    - Acceso a datos para las entidades respectivas.
2. **`ProductoDTO`, `ClienteDTO`**:
    - Objetos de transferencia de datos para encerrar datos complejos.
3. **`HelperMySQL`**:
    - Configura la conexión y gestiona las tablas.
4. **Entidad Relacional**:
    - Clases como `FacturaProducto`, `Factura`, `Producto` y `Cliente` representan tablas.

---

## 🔍 Descripción de Funcionalidades

1. **✅ Configuración de la Base de Datos**:
    - **Creación de tablas**:
        - `createTables()` crea las tablas requeridas con las restricciones apropiadas.
    - **Inserción de datos**:
        - Los datos son insertados al leer los archivos **CSV** usando la librería `commons-csv`.

2. **📊 Consultas y Reportes**:
    - **Producto con mayor recaudación**:
        - Calcula el producto que más ganancias ha generado, considerando la cantidad en las facturas.
    - **Clientes ordenados por facturación**:
        - Lista de clientes ordenados según el monto total de sus compras.

3. **🔗 Gestión de Dependencias**:
    - Implementa las claves foráneas necesarias para las restricciones de integridad:
        - `idCliente` en Factura.
        - `idProducto`, `idFactura` en FacturaProducto.

4. **🔒 Manejo Transaccional**:
    - El sistema emplea commits y rollbacks para mantener una conexión estable y evitar inconsistencias.

---