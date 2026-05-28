package modelo;

import java.util.List;

public class ReglaLuz implements ReglaCasa {

    @Override
    public void aplicar(List<SensorBase> sensores, List<ActuadorBase> actuadores) {
        double luz = -1;
        double presencia = 0;

        for (int i = 0; i < sensores.size(); i++) {
            SensorBase s = sensores.get(i);
            if (s.getID().equals("luminosidad")) {
                luz = s.getValor();
            } else if (s.getID().equals("presencia")) {
                presencia = s.getValor();
            }
        }
        if (luz == -1) return; // sensor no encontrado

        // encendemos si hay poca luz y alguien esta presente
        for (int i = 0; i < actuadores.size(); i++) {
            ActuadorBase a = actuadores.get(i);
            if (a.getID().equals("luz")) {
                if (luz < 200 && presencia == 1) {
                    a.ejecutarAccion("ON");
                } else {
                    a.ejecutarAccion("OFF");
                }
                break;
            }
        }
    }

    @Override
    public String getDescripcion() {
        return "R2. Control de luz por luminosidad y movimiento";
    }
}
