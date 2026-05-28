package modelo;

public class Bombilla extends ActuadorBase {

    public Bombilla() {
        super("luz", "Luz Principal", "OFF");
    }

    @Override
    public void ejecutarAccion(String accion) {
        if (accion.equals("ON") || accion.equals("OFF")) {
            this.estado = accion;
        }
    }

    // Acciones que acepta este actuador
    @Override
    public String[] getAccionesPosibles() {
        return new String[]{"ON", "OFF"};
    }
}
