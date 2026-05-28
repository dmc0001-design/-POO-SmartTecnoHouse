package modelo;

import java.util.List;

public class ReglaVentilacion implements ReglaCasa {

    @Override
    public void aplicar(List<SensorBase> sensores, List<ActuadorBase> actuadores) {
        double tempActual = -999; // centinela: si sigue en -999 es que no hay sensor
        for (int i = 0; i < sensores.size(); i++) {
            SensorBase s = sensores.get(i);
            if (s.getID().equals("temperatura")) {
                tempActual = s.getValor();
                break;
            }
        }
        if (tempActual == -999) return;

        for (int i = 0; i < actuadores.size(); i++) {
            ActuadorBase a = actuadores.get(i);
            if (a.getID().equals("ventil")) {
                if (tempActual >= 28) {
                    a.ejecutarAccion("HIGH");
                } else if (tempActual >= 24) {
                    a.ejecutarAccion("MID");
                } else if (tempActual >= 21) {
                    a.ejecutarAccion("LOW");
                } else {
                    a.ejecutarAccion("OFF");
                }
                break;
            }
        }
    }

    @Override
    public String getDescripcion() {
        return "R1. Control del ventilador por temperatura";
    }
}
