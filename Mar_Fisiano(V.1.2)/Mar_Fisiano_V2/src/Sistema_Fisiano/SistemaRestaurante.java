package Sistema_Fisiano;

import java.util.Map;
import java.util.Map.Entry;

public class SistemaRestaurante {
    private Inventario inventario;
    private Reporte reporte;

    public SistemaRestaurante() {
        inventario = new Inventario();
        reporte = new Reporte();
    }

    public Inventario getInventario() {
        return inventario;
    }

    public boolean procesarPedido(Pedido pedido) {
        for (Producto producto : pedido.getProductos()) {
            for (Entry<?, ?> entry : producto.getInsumosNecesarios().entrySet()) {
                Insumo insumo = inventario.obtenerInsumo(entry.getKey());
                if (insumo == null || insumo.getCantidadDisponible() < entry.getValue()) {
                    System.out.println("Falta insumo: " + entry.getKey() + " para el producto " + producto.getNombre());
                    return false;
                }
            }
        }

        for (Producto producto : pedido.getProductos()) {
            for (Entry<?, ?> entry : producto.getInsumosNecesarios().entrySet()) {
                Insumo insumo = inventario.obtenerInsumo(entry.getKey());
                insumo.consumir(entry.getValue());
            }
        }

        reporte.agregarPedido(pedido);
        System.out.println("Pedido procesado exitosamente.");
        return true;
    }

    public void generarReporte() {
        reporte.generarReporte();
    }
}
