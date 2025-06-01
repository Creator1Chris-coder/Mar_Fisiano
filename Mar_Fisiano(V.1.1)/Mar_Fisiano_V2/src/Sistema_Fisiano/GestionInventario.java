package Sistema_Fisiano;

import java.util.*;

public class GestionInventario {
    private Map<String, Insumo> inventario;

    public GestionInventario() {
        this.inventario = new HashMap<>();
    }

    public void agregarInsumo(Insumo insumo) {
        inventario.put(insumo.nombre, insumo);
    }

    public void actualizarCantidad(String nombre, double nuevaCantidad) {
        if (inventario.containsKey(nombre)) {
            inventario.get(nombre).cantidad = nuevaCantidad;
            System.out.println("Cantidad actualizada para: " + nombre);
        } else {
            System.out.println("Insumo no encontrado: " + nombre);
        }
    }

    public void descontarStock(String nombre, double cantidad) {
        if (inventario.containsKey(nombre)) {
            Insumo insumo = inventario.get(nombre);
            insumo.cantidad = Math.max(insumo.cantidad - cantidad, 0);
        }
    }

    public boolean estaDisponible(String nombre) {
        Insumo insumo = inventario.get(nombre);
        return insumo != null && insumo.cantidad > insumo.nivelMinimo;
    }

    public void mostrarInventario() {
        System.out.println("Inventario:");
        for (Insumo ins : inventario.values()) {
            System.out.println(ins);
        }
    }
}