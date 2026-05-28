package modelo;

import java.util.Random;

public class Termometro extends SensorBase {

    private Random rand;

    public Termometro() {
        super("temperatura", "Termometro", "C");
        this.rand = new Random();
    }

    @Override
    public void actualizarValor() {
        double nueva = 15 + (rand.nextDouble() * 20);
        this.ultimoValor = Math.round(nueva * 10.0) / 10.0; // un decimal
    }
}
