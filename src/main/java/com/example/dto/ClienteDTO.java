package com.example.dto;

public class ClienteDTO {
    private String nombre;
    private String email;
    private Double totalRecaudado;

    public ClienteDTO() {
    }

    public ClienteDTO(String nombre, String email, Double totalRecaudado) {
        this.nombre = nombre;
        this.email = email;
        this.totalRecaudado = totalRecaudado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getTotalRecaudado() {
        return totalRecaudado;
    }

    public void setTotalRecaudado(Double totalRecaudado) {
        this.totalRecaudado = totalRecaudado;
    }

    // Ancho fijo para el contenido (sin contar los bordes)
    private static final int CONTENT_WIDTH = 30;

    // Método auxiliar para truncar o rellenar un texto hasta que alcance una longitud fija
    private String formatField(String text) {
        if (text.length() > CONTENT_WIDTH) {
            // Trunca el texto si es demasiado largo, agregando "..."
            return text.substring(0, CONTENT_WIDTH - 3) + "...";
        } else {
            // Rellena a la derecha con espacios para tener longitud exacta
            return String.format("%-" + CONTENT_WIDTH + "s", text);
        }
    }

    // Método auxiliar para formatear una línea con clave y valor
    private String formatLine(String key, String value) {
        String lineContent = key + " : " + value;
        // Asegurarse que la línea tenga el CONTENT_WIDTH
        if (lineContent.length() > CONTENT_WIDTH) {
            lineContent = lineContent.substring(0, CONTENT_WIDTH);
        } else {
            lineContent = String.format("%-" + CONTENT_WIDTH + "s", lineContent);
        }
        return "│ " + lineContent + " │";
    }

    @Override
    public String toString() {
        // Líneas superior e inferior del recuadro
        String borderLine = "┌" + "─".repeat(CONTENT_WIDTH + 2) + "┐";
        String bottomBorder = "└" + "─".repeat(CONTENT_WIDTH + 2) + "┘";
        // Línea central para el título (centrado)
        String title = "Cliente";
        int padTotal = CONTENT_WIDTH - title.length();
        int padStart = padTotal / 2;
        int padEnd = padTotal - padStart;
        String titleLine = "│ " + " ".repeat(padStart) + title + " ".repeat(padEnd) + " │";

        // Crear las líneas para cada dato
        String nombreLine = formatLine("Nombre", formatField(nombre));
        String emailLine = formatLine("Email", formatField(email));
        // Para el total, se formatea el número con dos decimales
        String totalLine = formatLine("Total Recaudado", String.format("$%.2f", totalRecaudado));

        // Línea divisoria entre el título y los datos
        String divider = "├" + "─".repeat(CONTENT_WIDTH + 2) + "┤";

        // Agregamos un salto de línea antes y después del cuadro
        return
                borderLine + "\n" +
                titleLine + "\n" +
                divider + "\n" +
                nombreLine + "\n" +
                emailLine + "\n" +
                totalLine + "\n" +
                bottomBorder;
    }
}
