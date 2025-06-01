package Sistema_Fisiano;

import java.util.*;

public class GestionPromociones {
    private List<Promocion> promociones;

    public GestionPromociones() {
        this.promociones = new ArrayList<>();
    }

    public void agregarPromocion(Promocion promo) {
        promociones.add(promo);
        System.out.println("Promoción agregada: " + promo.nombre);
    }

    public List<Promocion> obtenerPromocionesActivas() {
        Date hoy = new Date();
        List<Promocion> activas = new ArrayList<>();
        for (Promocion p : promociones) {
            if (p.estaActiva()) {
                activas.add(p);
            }
        }
        return activas;
    }

    public void mostrarPromocionesActivas() {
        System.out.println("Promociones activas:");
        for (Promocion p : obtenerPromocionesActivas()) {
            System.out.println(p);
        }
    }
}

