package Sistema_Fisiano;

import java.util.*;

public class GestionPedidos {
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