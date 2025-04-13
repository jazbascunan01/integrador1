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

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", totalRecaudado=" + totalRecaudado +
                '}';
    }
}
