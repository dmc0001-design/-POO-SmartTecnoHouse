package modelo;

import java.util.Random;

public class SensorLuminosidad extends SensorBase {

    private Random rand;

    public SensorLuminosidad() {
        super("luminosidad", "Sensor Luminosidad", "lux");
        this.rand = new Random();
    }

    @Override
    public void actualizarValor() {
        // Lectura simulada entre 0 y 1000 lux
        this.ultimoValor = rand.nextInt(1001);
    }
}
