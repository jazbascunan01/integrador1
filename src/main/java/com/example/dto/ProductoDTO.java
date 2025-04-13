package com.example.dto;
public class ProductoDTO {
    private String nombre;
    private float valor;
    private Double recaudacion;

    public ProductoDTO() {
    }

    public ProductoDTO(String nombre, float valor, Double recaudacion) {
        this.nombre = nombre;
        this.valor = valor;
        this.recaudacion = recaudacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Double getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(Double recaudacion) {
        this.recaudacion = recaudacion;
    }

    // Ancho fijo para el contenido (sin contar los bordes)
    private static final int CONTENT_WIDTH = 40;

    // Método auxiliar para truncar o rellenar un texto hasta que alcance una longitud fija
    private String formatField(String text) {
        if (text.length() > CONTENT_WIDTH) {
            return text.substring(0, CONTENT_WIDTH - 3) + "...";
        } else {
            return String.format("%-" + CONTENT_WIDTH + "s", text);
        }
    }

    // Método auxiliar para formatear una línea con clave y valor
    private String formatLine(String key, String value) {
        String lineContent = key + " : " + value;
        if (lineContent.length() > CONTENT_WIDTH) {
            lineContent = lineContent.substring(0, CONTENT_WIDTH);
        } else {
            lineContent = String.format("%-" + CONTENT_WIDTH + "s", lineContent);
        }
        return "║ " + lineContent + " ║";
    }

    @Override
    public String toString() {
        // Bordes superior e inferior usando caracteres dobles para un toque elegante
        String topBorder = "╔" + "═".repeat(CONTENT_WIDTH + 2) + "╗";
        String bottomBorder = "╚" + "═".repeat(CONTENT_WIDTH + 2) + "╝";

        // Título centrado
        String title = "Producto";
        int padTotal = CONTENT_WIDTH - title.length();
        int padLeft = padTotal / 2;
        int padRight = padTotal - padLeft;
        String titleLine = "║ " + " ".repeat(padLeft) + title + " ".repeat(padRight) + " ║";

        // Línea divisoria entre el título y los datos
        String divider = "╠" + "═".repeat(CONTENT_WIDTH + 2) + "╣";

        // Líneas para cada dato
        String nombreLine = formatLine("Nombre", formatField(nombre));
        String valorLine = formatLine("Valor", String.format("$%.2f", valor));
        String recaudacionLine = formatLine("Recaudación", String.format("$%.2f", recaudacion));

        // Agregamos un salto de línea antes y después del recuadro
        return
                topBorder + "\n" +
                titleLine + "\n" +
                divider + "\n" +
                nombreLine + "\n" +
                valorLine + "\n" +
                recaudacionLine + "\n" +
                bottomBorder;
    }
}
