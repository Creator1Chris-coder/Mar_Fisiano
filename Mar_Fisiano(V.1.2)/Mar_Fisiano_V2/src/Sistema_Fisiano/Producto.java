package Sistema_Fisiano;

import java.util.Map;

public class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return nombre + " - $" + precio;
    }

	Map<?, ?> getInsumosNecesarios() {
		// TODO Auto-generated method stub
		return null;
	}

	public void agregarInsumo(String string, int i) {
		// TODO Auto-generated method stub
		
	}
}

