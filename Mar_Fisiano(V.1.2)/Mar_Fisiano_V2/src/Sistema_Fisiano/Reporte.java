package Sistema_Fisiano;

import java.util.ArrayList;
import java.util.List;

public class Reporte {
    private List<Pedido> pedidosRealizados;

    public Reporte() {
        pedidosRealizados = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidosRealizados.add(pedido);
    }

    public void generarReporte() {
        System.out.println("=== Reporte de Ventas ===");
        double totalGeneral = 0;
        for (Pedido pedido : pedidosRealizados) {
            System.out.println(pedido);
            totalGeneral += pedido.getTotal();
        }
        System.out.println("Total de ingresos: $" + totalGeneral);
    }
}
