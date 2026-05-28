package modelo;

// Enchufe inteligente. Lleva un contador de cuantas veces se enciende.
public class Enchufe extends ActuadorBase {

    private int vecesEncendido;

    public Enchufe() {
        super("enchufe", "Enchufe Smart", "OFF");
    }

    @Override
    public void ejecutarAccion(String accion) {
        if (accion.equals("ON")) {
            this.estado = accion;
            vecesEncendido++;
        } else if (accion.equals("OFF")) {
            this.estado = accion;
        }
    }

    @Override
    public String[] getAccionesPosibles() {
        return new String[]{"ON", "OFF"};
    }

    public int getVecesEncendido() {
        return vecesEncendido;
    }
}
