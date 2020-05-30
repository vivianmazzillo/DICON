package com.example.appdicon;

public class Categorias
{
    public String Nombre;
    public String Precio;
    public Categorias () {
    }

    public Categorias(String Nombre, String Precio) {
        this.Nombre = Nombre;
        this.Precio = Precio;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String Precio) {
        this.Precio = Precio;
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
}
