package Sistema_Fisiano;

import java.util.HashMap;
import java.util.Map;

public class Inventario {
    private Map<String, Insumo> insumos;

    public Inventario() {
        insumos = new HashMap<>();
    }

    public void agregarInsumo(Insumo insumo) {
        insumos.put(insumo.getNombre(), insumo);
    }

    public Insumo obtenerInsumo(Object object) {
        return insumos.get(object);
    }

    public void mostrarInventario() {
        for (Insumo insumo : insumos.values()) {
            System.out.println(insumo);
        }
    }
}