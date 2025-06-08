package Sistema_Fisiano;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
			SistemaRestaurante sistema = new SistemaRestaurante();

			Producto hamburguesa = new Producto("Hamburguesa", 5.99);
			hamburguesa.agregarInsumo("Pan", 1);
			hamburguesa.agregarInsumo("Carne", 1);
			hamburguesa.agregarInsumo("Queso", 1);

			Producto papas = new Producto("Papas Fritas", 2.50);
			papas.agregarInsumo("Papa", 2);
			papas.agregarInsumo("Sal", 1);

			Producto soda = new Producto("Refresco", 1.99);
			soda.agregarInsumo("Vaso", 1);
			soda.agregarInsumo("Refresco", 1);

			sistema.getInventario().agregarInsumo(new Insumo("Pan", 10));
			sistema.getInventario().agregarInsumo(new Insumo("Carne", 10));
			sistema.getInventario().agregarInsumo(new Insumo("Queso", 10));
			sistema.getInventario().agregarInsumo(new Insumo("Papa", 10));
			sistema.getInventario().agregarInsumo(new Insumo("Sal", 10));
			sistema.getInventario().agregarInsumo(new Insumo("Vaso", 10));
			sistema.getInventario().agregarInsumo(new Insumo("Refresco", 10));

			boolean salir = false;
			while (!salir) {
			    System.out.println("\n--- MENÚ PRINCIPAL ---");
			    System.out.println("1. Realizar Pedido");
			    System.out.println("2. Ver Reporte de Ventas");
			    System.out.println("3. Ver Inventario");
			    System.out.println("4. Agregar Insumo al Inventario");
			    System.out.println("5. Salir");
			    System.out.print("Selecciona una opción: ");
			    int opcion = Integer.parseInt(scanner.nextLine());

			    switch (opcion) {
			        case 1 -> {
			            Pedido pedido = new Pedido();
			            boolean pedir = true;
			            while (pedir) {
			                System.out.println("\n--- MENÚ DE PRODUCTOS ---");
			                System.out.println("1. " + hamburguesa);
			                System.out.println("2. " + papas);
			                System.out.println("3. " + soda);
			                System.out.println("4. Finalizar pedido");
			                System.out.print("Elige un producto: ");
			                int seleccion = Integer.parseInt(scanner.nextLine());

			                switch (seleccion) {
			                    case 1 -> pedido.agregarProducto(hamburguesa);
			                    case 2 -> pedido.agregarProducto(papas);
			                    case 3 -> pedido.agregarProducto(soda);
			                    case 4 -> {
			                        pedir = false;
			                        boolean exito = sistema.procesarPedido(pedido);
			                        if (!exito) {
			                            System.out.println("El pedido no pudo procesarse por falta de insumos.");
			                        }
			                    }
			                    default -> System.out.println("Opción no válida.");
			                }
			            }
			        }

			        case 2 -> sistema.generarReporte();

			        case 3 -> {
			            System.out.println("\n--- INVENTARIO ---");
			            sistema.getInventario().mostrarInventario();
			        }

			        case 4 -> {
			            System.out.print("Nombre del insumo: ");
			            String nombre = scanner.nextLine();
			            System.out.print("Cantidad a agregar: ");
			            int cantidad = Integer.parseInt(scanner.nextLine());
			            Insumo existente = sistema.getInventario().obtenerInsumo(nombre);
			            if (existente != null) {
			                existente.agregarCantidad(cantidad);
			                System.out.println("Cantidad actualizada.");
			            } else {
			                sistema.getInventario().agregarInsumo(new Insumo(nombre, cantidad));
			                System.out.println("Insumo agregado al inventario.");
			            }
			        }

			        case 5 -> {
			            salir = true;
			            System.out.println("Gracias por usar el sistema.");
			        }

			        default -> System.out.println("Opción inválida.");
			    }
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
    }
}
