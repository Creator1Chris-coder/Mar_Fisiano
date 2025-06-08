package Sistema_Fisiano;

public class Insumo {
    private String nombre;
    private int cantidadDisponible;

    public Insumo(String nombre, int cantidadDisponible) {
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getNombre() { return nombre; }
    public int getCantidadDisponible() { return cantidadDisponible; }

    public void agregarCantidad(int cantidad) {
        this.cantidadDisponible += cantidad;
    }

    public boolean consumir(Object object) {
        if (object <= cantidadDisponible) {
            cantidadDisponible -= object;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return nombre + " (Stock: " + cantidadDisponible + ")";
    }
}