package modelo;

public class Ventilador extends ActuadorBase {

    public Ventilador() {
        super("ventil", "Ventilador Techo", "OFF");
    }

    @Override
    public void ejecutarAccion(String accion) {
        if (accion.equals("OFF") || accion.equals("LOW")
                || accion.equals("MID") || accion.equals("HIGH")) {
            this.estado = accion;
        }
    }

    // Tres velocidades mas el apagado
    @Override
    public String[] getAccionesPosibles() {
        return new String[]{"OFF", "LOW", "MID", "HIGH"};
    }
}
