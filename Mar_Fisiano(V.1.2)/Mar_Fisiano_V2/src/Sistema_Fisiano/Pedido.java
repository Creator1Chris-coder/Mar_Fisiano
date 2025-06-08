package Sistema_Fisiano;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Producto> productos;
    private double total;

    public Pedido() {
        productos = new ArrayList<>();
        total = 0.0;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        total += producto.getPrecio();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pedido:\n");
        for (Producto p : productos) {
            sb.append(" - ").append(p).append("\n");
        }
        sb.append("Total: $").append(total);
        return sb.toString();
    }
}