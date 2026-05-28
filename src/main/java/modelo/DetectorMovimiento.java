package modelo;

import java.util.Random;

public class DetectorMovimiento extends SensorBase {

    private Random rand;

    public DetectorMovimiento() {
        super("presencia", "Detector Movimiento", "");
        this.rand = new Random();
    }

    @Override
    public void actualizarValor() {
        this.ultimoValor = rand.nextInt(2); // 0 nadie, 1 hay alguien
    }

    @Override
    public String getEstadoActual() {
        if (ultimoValor == 1) {
            return "Detectado";
        }
        return "Sin movimiento";
    }
}
