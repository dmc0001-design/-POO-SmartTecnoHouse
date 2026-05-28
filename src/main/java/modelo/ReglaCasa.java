package modelo;

import java.util.List;

// Interfaz que tienen que seguir todas las reglas del sistema.
public interface ReglaCasa {
    void aplicar(List<SensorBase> sensores, List<ActuadorBase> actuadores);
    String getDescripcion();
}
