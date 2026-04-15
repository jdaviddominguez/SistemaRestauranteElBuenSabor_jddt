/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

/**
 *
 * @author alfre
 */
public class RestauranteElBuenSabor {

 private static final Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        imprimirBienvenida();
        ejecutarMenuPrincipal();
        entrada.close();
    }

    // -------------------------------------------------------------------------
    // Flujo principal
    // -------------------------------------------------------------------------

    private static void imprimirBienvenida() {
        System.out.println("========================================");
        System.out.println("    " + Restaurante.NOMBRE_COMPLETO);
        System.out.println("    " + Restaurante.DIRECCION);
        System.out.println("    NIT: " + Restaurante.NIT);
        System.out.println("========================================");
    }

    private static void ejecutarMenuPrincipal() {
        boolean ejecutando = true;
        while (ejecutando) {
            mostrarOpcionesMenu();
            int opcion = leerEnteroDesdeConsola();
            ejecutando = procesarOpcion(opcion);
        }
    }

    private static void mostrarOpcionesMenu() {
        System.out.println("1. Ver carta");
        System.out.println("2. Agregar producto al pedido");
        System.out.println("3. Ver pedido actual");
        System.out.println("4. Generar factura");
        System.out.println("5. Nueva mesa");
        System.out.println("0. Salir");
        System.out.println("========================================");
        System.out.print("Seleccione una opcion: ");
    }

    /**
     * Despacha la opción seleccionada al método correspondiente.
     *
     * @return false si el usuario eligió salir, true en cualquier otro caso.
     */
    private static boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                Imprimir.mostrarCarta();
                break;
            case 2:
                opcionAgregarProducto();
                break;
            case 3:
                opcionVerPedido();
                break;
            case 4:
                opcionGenerarFactura();
                break;
            case 5:
                opcionNuevaMesa();
                break;
            case 0:
                System.out.println("Hasta luego!");
                return false;
            default:
                System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
        }
        System.out.println();
        return true;
    }

    // -------------------------------------------------------------------------
    // Opciones del menú
    // -------------------------------------------------------------------------

    private static void opcionAgregarProducto() {
        System.out.println("--- AGREGAR PRODUCTO ---");

        int numeroProducto = pedirNumeroProducto();
        if (!esProductoValido(numeroProducto)) {
            return;
        }

        int cantidad = pedirCantidad();
        if (!esCantidadValida(cantidad)) {
            return;
        }

        if (!Restaurante.isMesaActiva()) {
            pedirYAsignarMesa();
        }

        int indice = numeroProducto - 1;
        Pedido.agregarProducto(indice, cantidad);
        System.out.println("Producto agregado al pedido.");
        System.out.println("  -> " + Restaurante.MENU[indice].getNombre() + " x" + cantidad);
    }

    private static void opcionVerPedido() {
        if (Pedido.hayProductos()) {
            Imprimir.mostrarPedido();
        } else {
            System.out.println("No hay productos en el pedido actual.");
            System.out.println("Use la opcion 2 para agregar productos.");
        }
    }

    private static void opcionGenerarFactura() {
        if (Pedido.hayProductos()) {
            Imprimir.imprimirFacturaCompleta();
        } else {
            System.out.println("No se puede generar factura.");
            System.out.println("No hay productos en el pedido.");
            System.out.println("Use la opcion 2 para agregar productos primero.");
        }
    }

    private static void opcionNuevaMesa() {
        Pedido.reiniciar();
        System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
    }

    // -------------------------------------------------------------------------
    // Métodos auxiliares de entrada
    // -------------------------------------------------------------------------

    private static int pedirNumeroProducto() {
        System.out.print("Numero de producto (1-" + Restaurante.MENU.length + "): ");
        return leerEnteroDesdeConsola();
    }

    private static int pedirCantidad() {
        System.out.print("Cantidad: ");
        return leerEnteroDesdeConsola();
    }

    private static void pedirYAsignarMesa() {
        System.out.print("Ingrese numero de mesa: ");
        int numeroMesa = leerEnteroDesdeConsola();
        if (numeroMesa <= 0) {
            System.out.println("Numero de mesa invalido. Se asignara la mesa 1.");
            numeroMesa = 1;
        }
        Restaurante.setNumeroMesa(numeroMesa);
    }

    private static boolean esProductoValido(int numero) {
        if (numero <= 0) {
            System.out.println("El numero debe ser mayor a cero.");
            return false;
        }
        if (numero > Restaurante.MENU.length) {
            System.out.println("Producto no existe. La carta tiene " + Restaurante.MENU.length + " productos.");
            return false;
        }
        return true;
    }

    private static boolean esCantidadValida(int cantidad) {
        if (cantidad == 0) {
            System.out.println("La cantidad no puede ser cero.");
            return false;
        }
        if (cantidad < 0) {
            System.out.println("Cantidad invalida. Ingrese un valor positivo.");
            return false;
        }
        return true;
    }

    private static int leerEnteroDesdeConsola() {
        return entrada.nextInt();
    }
}
