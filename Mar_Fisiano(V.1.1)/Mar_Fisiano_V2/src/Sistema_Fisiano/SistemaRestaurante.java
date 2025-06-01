package Sistema_Fisiano;

import java.text.SimpleDateFormat;
import java.util.*;

class Insumo {
    String nombre;
    String unidad;
    double cantidad;
    Date fechaVencimiento;
    double nivelMinimo;
    String categoria;

    public Insumo(String nombre, String unidad, double cantidad, Date fechaVencimiento, double nivelMinimo, String categoria) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.cantidad = cantidad;
        this.fechaVencimiento = fechaVencimiento;
        this.nivelMinimo = nivelMinimo;
        this.categoria = categoria;
    }

    public boolean estaDisponible() {
        return cantidad > nivelMinimo;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("%s: %.2f %s (Vence: %s)", nombre, cantidad, unidad, sdf.format(fechaVencimiento));
    }
}

class Producto {
    String nombre;
    String categoria;
    double precio;
    boolean disponible;
    List<String> ingredientes;

    public Producto(String nombre, String categoria, double precio, List<String> ingredientes) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.ingredientes = new ArrayList<>(ingredientes);
        this.disponible = true;
    }

    public void setDisponible(boolean disp) {
        this.disponible = disp;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f) - %s - %s", nombre, precio, categoria, disponible ? "Disponible" : "No disponible");
    }
}

class Pedido {
    List<Producto> productos;
    String estado;
    String observaciones;
    String tipoConsumo;

    public Pedido() {
        productos = new ArrayList<>();
        estado = "Pendiente";
        observaciones = "";
        tipoConsumo = "Salón";
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setObservaciones(String obs) {
        this.observaciones = obs;
    }

    public void setTipoConsumo(String tipo) {
        this.tipoConsumo = tipo;
    }

    @Override
    public String toString() {
        String prods = "";
        for (Producto p : productos) {
            prods += p.nombre + ", ";
        }
        if (prods.length() > 2) prods = prods.substring(0, prods.length()-2);
        return String.format("Pedido [%s] - Tipo: %s - Obs: %s - Productos: %s", estado, tipoConsumo, observaciones, prods);
    }
}

class Promocion {
    String nombre;
    List<String> productos;
    double precioPromocional;
    Date fechaInicio;
    Date fechaFin;

    public Promocion(String nombre, List<String> productos, double precioPromocional, Date fechaInicio, Date fechaFin) {
        this.nombre = nombre;
        this.productos = new ArrayList<>(productos);
        this.precioPromocional = precioPromocional;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public boolean estaActiva() {
        Date hoy = new Date();
        return hoy.after(fechaInicio) && hoy.before(fechaFin);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("Promoción %s: Productos %s - Precio promocional: %.2f - Valida de %s a %s", 
                             nombre, productos, precioPromocional, sdf.format(fechaInicio), sdf.format(fechaFin));
    }
}


class GestionMenu {
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

    public List<Producto> getMenu() {
        return menu;
    }
}

class GestionInventario {
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

    public Map<String, Insumo> getInventario() {
        return inventario;
    }
}

class GestionPromociones {
    private List<Promocion> promociones;

    public GestionPromociones() {
        this.promociones = new ArrayList<>();
    }

    public void agregarPromocion(Promocion promo) {
        promociones.add(promo);
        System.out.println("Promoción agregada: " + promo.nombre);
    }

    public List<Promocion> obtenerPromocionesActivas() {
        List<Promocion> activas = new ArrayList<>();
        for (Promocion p : promociones) {
            if (p.estaActiva()) {
                activas.add(p);
            }
        }
        return activas;
    }

    public void mostrarPromocionesActivas() {
        System.out.println("Promociones activas:");
        for (Promocion p : obtenerPromocionesActivas()) {
            System.out.println(p);
        }
    }
}

class GestionPedidos {
    private Queue<Pedido> colaPedidos;

    public GestionPedidos() {
        colaPedidos = new LinkedList<>();
    }

    public void agregarPedido(Pedido pedido) {
        colaPedidos.add(pedido);
        System.out.println("Pedido agregado a la cola: " + pedido);
    }

    public Pedido siguientePedido() {
        return colaPedidos.peek();
    }

    public Pedido procesarPedido() {
        Pedido pedido = colaPedidos.poll();
        if (pedido != null) {
            pedido.setEstado("Procesado");
            System.out.println("Pedido procesado: " + pedido);
        } else {
            System.out.println("No hay pedidos en la cola.");
        }
        return pedido;
    }

    public void mostrarPedidosPendientes() {
        System.out.println("Pedidos pendientes:");
        for (Pedido p : colaPedidos) {
            System.out.println(p);
        }
    }
}

class GestionReportes {
    private List<Pedido> ventas;

    public GestionReportes() {
        ventas = new ArrayList<>();
    }

    public void registrarVenta(Pedido pedido) {
        ventas.add(pedido);
        System.out.println("Venta registrada: " + pedido);
    }

    public void mostrarReporteVentas() {
        System.out.println("Reporte de ventas:");
        for (Pedido p : ventas) {
            System.out.println(p);
        }
        System.out.println("Total ventas: " + ventas.size());
    }
}

public class SistemaRestauranteModular {
    GestionMenu gestionMenu;
    GestionInventario gestionInventario;
    GestionPromociones gestionPromociones;
    GestionPedidos gestionPedidos;
    GestionReportes gestionReportes;

    public SistemaRestauranteModular() {
        gestionMenu = new GestionMenu();
        gestionInventario = new GestionInventario();
        gestionPromociones = new GestionPromociones();
        gestionPedidos = new GestionPedidos();
        gestionReportes = new GestionReportes();
    }

    public void actualizarDisponibilidadProductos() {
        for (Producto p : gestionMenu.getMenu()) {
            boolean disponible = true;
            for (String ingr : p.ingredientes) {
                if (!gestionInventario.estaDisponible(ingr)) {
                    disponible = false;
                    break;
                }
            }
            p.setDisponible(disponible);
        }
    }

    public void procesarSiguientePedido() {
        Pedido pedido = gestionPedidos.procesarPedido();
        if (pedido != null) {
            for (Producto p : pedido.productos) {
                for (String ingr : p.ingredientes) {
                    gestionInventario.descontarStock(ingr, 1); 
                }
            }
            gestionReportes.registrarVenta(pedido);
            actualizarDisponibilidadProductos();
        }
    }

    public static void main(String[] args) throws Exception {
        SistemaRestauranteModular sistema = new SistemaRestauranteModular();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        sistema.gestionInventario.agregarInsumo(new Insumo("Pescado", "kg", 10, sdf.parse("30/06/2025"), 2, "Alimentos"));
        sistema.gestionInventario.agregarInsumo(new Insumo("Cebolla", "kg", 5, sdf.parse("15/06/2025"), 1, "Alimentos"));
        sistema.gestionInventario.agregarInsumo(new Insumo("Limón", "kg", 7, sdf.parse("20/06/2025"), 1, "Alimentos"));

        Producto ceviche = new Producto("Ceviche Mixto", "Ceviches", 25.0, Arrays.asList("Pescado", "Cebolla", "Limón"));
        Producto lecheTigre = new Producto("Leche de Tigre", "Entradas", 15.0, Arrays.asList("Pescado", "Limón"));

        sistema.gestionMenu.registrarProducto(ceviche);
        sistema.gestionMenu.registrarProducto(lecheTigre);

        sistema.actualizarDisponibilidadProductos();

        sistema.gestionMenu.mostrarMenu();
        sistema.gestionInventario.mostrarInventario();

        Promocion promo = new Promocion("Promo Ceviche + Bebida", Arrays.asList("Ceviche Mixto", "Chicha"), 30.0,
                                        sdf.parse("01/06/2025"), sdf.parse("30/06/2025"));
        sistema.gestionPromociones.agregarPromocion(promo);
        sistema.gestionPromociones.mostrarPromocionesActivas();

        Pedido pedido1 = new Pedido();
        pedido1.agregarProducto(ceviche);
        pedido1.setTipoConsumo("Delivery");
        pedido1.setObservaciones("Sin cebolla");

        sistema.gestionPedidos.agregarPedido(pedido1);

        sistema.gestionPedidos.mostrarPedidosPendientes();

        sistema.procesarSiguientePedido();

        sistema.gestionReportes.mostrarReporteVentas();

        sistema.gestionInventario.mostrarInventario();

        sistema.gestionMenu.mostrarMenu();
    }
}

