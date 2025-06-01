package Sistema_Fisiano;

import java.util.*;

public class GestionMenu {
    private List<Producto> menu;

    public GestionMenu() {
        this.menu = new ArrayList<>();
    }

    public void registrarProducto(Producto p) {
        menu.add(p);
        System.out.println("Producto registrado: " + p.nombre);
    }

    public void editarProducto(String nombre, double nuevoPrecio) {
        for (Producto p : menu) {
            if (p.nombre.equalsIgnoreCase(nombre)) {
                p.precio = nuevoPrecio;
                System.out.println("Producto modificado: " + p);
                return;
            }
        }
        System.out.println("Producto no encontrado para editar.");
    }

    public void eliminarProducto(String nombre) {
        menu.removeIf(p -> p.nombre.equalsIgnoreCase(nombre));
        System.out.println("Producto eliminado si existía: " + nombre);
    }

    public List<Producto> filtrarPorCategoria(String categoria) {
        List<Producto> filtrados = new ArrayList<>();
        for (Producto p : menu) {
            if (p.categoria.equalsIgnoreCase(categoria)) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }

    public void mostrarMenu() {
        System.out.println("Menú completo:");
        for (Producto p : menu) {
            System.out.println(p);
        }
    }
}
