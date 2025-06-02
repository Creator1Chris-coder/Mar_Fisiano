package Sistema_Fisiano;

import java.util.Scanner;
import java.util.List;

public class ProgramaInteractivo {
    private GestionMenu gestionMenu;
    private Scanner scanner;

    public ProgramaInteractivo() {
        gestionMenu = new GestionMenu();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        boolean salir = false;

        while (!salir) {
            mostrarMenuOpciones();
            int opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    registrarProducto();
                    break;
                case 2:
                    editarProducto();
                    break;
                case 3:
                    eliminarProducto();
                    break;
                case 4:
                    mostrarMenu();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida, intente de nuevo.");
            }
        }
        scanner.close();
    }

    private void mostrarMenuOpciones() {
        System.out.println("\n--- Menú de opciones ---");
        System.out.println("1. Registrar producto");
        System.out.println("2. Editar precio de producto");
        System.out.println("3. Eliminar producto");
        System.out.println("4. Mostrar menú completo");
        System.out.println("5. Salir");
    }

    private void registrarProducto() {
        System.out.println("Registrar nuevo producto");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();

        double precio = leerDouble("Precio: ");

        System.out.print("Ingredientes (separados por coma): ");
        String ingredientesStr = scanner.nextLine();
        String[] ingredientesArray = ingredientesStr.split(",");
        for (int i = 0; i < ingredientesArray.length; i++) {
            ingredientesArray[i] = ingredientesArray[i].trim();
        }

        Producto producto = new Producto(nombre, categoria, precio, List.of(ingredientesArray));
        gestionMenu.registrarProducto(producto);
    }

    private void editarProducto() {
        System.out.println("Editar producto");
        System.out.print("Nombre del producto a editar: ");
        String nombre = scanner.nextLine();

        double nuevoPrecio = leerDouble("Nuevo precio: ");
        gestionMenu.editarProducto(nombre, nuevoPrecio);
    }

    private void eliminarProducto() {
        System.out.println("Eliminar producto");
        System.out.print("Nombre del producto a eliminar: ");
        String nombre = scanner.nextLine();

        gestionMenu.eliminarProducto(nombre);
    }

    private void mostrarMenu() {
        gestionMenu.mostrarMenu();
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String linea = scanner.nextLine();
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, por favor ingrese un número entero.");
            }
        }
    }

    private double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String linea = scanner.nextLine();
                return Double.parseDouble(linea);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, por favor ingrese un número decimal.");
            }
        }
    }

    public static void main(String[] args) {
        ProgramaInteractivo programa = new ProgramaInteractivo();
        programa.iniciar();
    }
}
