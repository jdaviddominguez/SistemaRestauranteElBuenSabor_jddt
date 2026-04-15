package com.mycompany.restauranteelbuensabor;

public class Restaurante {

    // --- Identidad del restaurante (constantes, un único lugar) ---
    public static final String NOMBRE        = "El Buen Sabor";
    public static final String NOMBRE_COMPLETO = "RESTAURANTE EL BUEN SABOR";
    public static final String DIRECCION     = "Calle 15 #8-32, Valledupar";
    public static final String NIT           = "900.123.456-7";

    // --- Menú (array de objetos, no tres arrays paralelos) ---
    public static final Producto[] MENU = {
        new Producto("Bandeja Paisa",      32000),
        new Producto("Sancocho de Gallina", 28000),
        new Producto("Arepa con Huevo",     8000),
        new Producto("Jugo Natural",         7000),
        new Producto("Gaseosa",              4500),
        new Producto("Cerveza Poker",        6000),
        new Producto("Agua Panela",          3500),
        new Producto("Arroz con Pollo",     25000),
    };

    // --- Estado de la sesión activa ---
    private static int numeroMesa    = 0;
    private static boolean mesaActiva = false;
    private static int numeroFactura = 1;

    // Constructor privado: esta clase no se instancia
    private Restaurante() {}

    public static int getNumeroMesa() {
        return numeroMesa;
    }

    public static void setNumeroMesa(int mesa) {
        numeroMesa = mesa;
        mesaActiva = true;
    }

    public static boolean isMesaActiva() {
        return mesaActiva;
    }

    public static int getNumeroFactura() {
        return numeroFactura;
    }

    public static void incrementarNumeroFactura() {
        numeroFactura++;
    }

    /**
     * Reinicia el estado completo de la sesión para atender una nueva mesa.
     */
    public static void reiniciarSesion() {
        for (Producto producto : MENU) {
            producto.reiniciarCantidad();
        }
        numeroMesa = 0;
        mesaActiva = false;
    }
}
