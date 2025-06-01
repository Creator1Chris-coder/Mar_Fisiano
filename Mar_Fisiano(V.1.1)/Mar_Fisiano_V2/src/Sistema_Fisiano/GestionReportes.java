package Sistema_Fisiano;

import java.util.*;

public class GestionReportes {
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
