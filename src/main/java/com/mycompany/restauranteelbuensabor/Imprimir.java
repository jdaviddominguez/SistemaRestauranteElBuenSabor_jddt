/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor;

/**
 *
 * @author alfre
 */
public class Imprimir {

    private static final String SEPARADOR_DOBLE  = "========================================";
    private static final String SEPARADOR_SIMPLE = "----------------------------------------";
    private static final String FMT_LINEA_MONEDA = "%-27s $%,.0f%n";

    // Constructor privado: clase de utilidad estática
    private Imprimir() {}

    // -------------------------------------------------------------------------
    // Métodos públicos
    // -------------------------------------------------------------------------

    public static void mostrarCarta() {
        System.out.println(SEPARADOR_DOBLE);
        System.out.println("    " + Restaurante.NOMBRE_COMPLETO);
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR_DOBLE);
        for (int i = 0; i < Restaurante.MENU.length; i++) {
            Producto p = Restaurante.MENU[i];
            System.out.printf("%d. %-22s $%,.0f%n", i + 1, p.getNombre(), p.getPrecio());
        }
        System.out.println(SEPARADOR_DOBLE);
    }

    public static void mostrarPedido() {
        System.out.println("--- PEDIDO ACTUAL ---");
        double subtotal = 0;
        for (Producto producto : Restaurante.MENU) {
            if (producto.fueOrdenado()) {
                System.out.printf("%-20s x%-6d $%,.0f%n",
                    producto.getNombre(),
                    producto.getCantidad(),
                    producto.calcularSubtotalItem());
                subtotal += producto.calcularSubtotalItem();
            }
        }
        System.out.println("--------------------");
        System.out.printf(FMT_LINEA_MONEDA, "Subtotal:", subtotal);
    }

    public static void imprimirFacturaCompleta() {
        double subtotal         = Proceso.calcularSubtotal();
        double subtotalDescuento = Proceso.aplicarDescuento(subtotal);
        double iva               = Proceso.calcularIva(subtotalDescuento);
        double propina           = Proceso.calcularPropina(subtotalDescuento);
        double total             = subtotalDescuento + iva + propina;

        imprimirEncabezadoFactura(false);
        imprimirLineasPedido();
        imprimirTotales(subtotalDescuento, iva, propina, total);
        imprimirPie();

        Restaurante.incrementarNumeroFactura();
    }

    public static void imprimirFacturaResumen() {
        double subtotal         = Proceso.calcularSubtotal();
        double subtotalDescuento = Proceso.aplicarDescuento(subtotal);
        double iva               = Proceso.calcularIva(subtotalDescuento);
        double propina           = Proceso.calcularPropina(subtotalDescuento);
        double total             = subtotalDescuento + iva + propina;

        imprimirEncabezadoFactura(true);
        imprimirTotales(subtotalDescuento, iva, propina, total);
        System.out.println(SEPARADOR_DOBLE);
    }

    // -------------------------------------------------------------------------
    // Métodos privados compartidos (elimina el 80% de código duplicado)
    // -------------------------------------------------------------------------

    private static void imprimirEncabezadoFactura(boolean esResumen) {
        System.out.println(SEPARADOR_DOBLE);
        System.out.println("    " + Restaurante.NOMBRE_COMPLETO);
        System.out.println("    " + Restaurante.DIRECCION);
        System.out.println("    NIT: " + Restaurante.NIT);
        System.out.println(SEPARADOR_DOBLE);
        String etiqueta = esResumen ? " (RESUMEN)" : "";
        System.out.printf("FACTURA No. %03d%s%n", Restaurante.getNumeroFactura(), etiqueta);
        System.out.println(SEPARADOR_SIMPLE);
    }

    private static void imprimirLineasPedido() {
        for (Producto producto : Restaurante.MENU) {
            if (producto.fueOrdenado()) {
                System.out.printf("%-20s x%-6d $%,.0f%n",
                    producto.getNombre(),
                    producto.getCantidad(),
                    producto.calcularSubtotalItem());
            }
        }
        System.out.println(SEPARADOR_SIMPLE);
    }

    private static void imprimirTotales(double subtotal, double iva, double propina, double total) {
        System.out.printf(FMT_LINEA_MONEDA, "Subtotal:", subtotal);
        System.out.printf(FMT_LINEA_MONEDA, "IVA (19%):", iva);
        if (propina > 0) {
            System.out.printf(FMT_LINEA_MONEDA, "Propina (10%):", propina);
        }
        System.out.println(SEPARADOR_SIMPLE);
        System.out.printf(FMT_LINEA_MONEDA, "TOTAL:", total);
    }

    private static void imprimirPie() {
        System.out.println(SEPARADOR_DOBLE);
        System.out.println("Gracias por su visita!");
        System.out.println(Restaurante.NOMBRE + " - " + Restaurante.DIRECCION);
        System.out.println(SEPARADOR_DOBLE);
    }
}
