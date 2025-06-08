package Sistema_Fisiano;

public class Promocion {
    private String descripcion;
    private double descuentoPorcentaje;

    public Promocion(String descripcion, double descuentoPorcentaje) {
        this.descripcion = descripcion;
        this.descuentoPorcentaje = descuentoPorcentaje;
    }

    public String getDescripcion() { return descripcion; }
    public double getDescuentoPorcentaje() { return descuentoPorcentaje; }

    public double aplicarDescuento(double total) {
        return total * (1 - descuentoPorcentaje / 100);
    }

    @Override
    public String toString() {
        return descripcion + " (" + descuentoPorcentaje + "% de descuento)";
    }
}
