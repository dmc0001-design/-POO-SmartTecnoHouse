package modelo;

import java.util.Random;

// Sensor de humedad. Simula lecturas entre 20% y 90%.
public class Higrometro extends SensorBase {

    private Random rand;

    public Higrometro() {
        super("humedad", "Higrometro", "%");
        this.rand = new Random();
    }

    @Override
    public void actualizarValor() {
        // Entre 20% y 90% para que sea realista en interior
        double nueva = 20 + (rand.nextDouble() * 70);
        this.ultimoValor = Math.round(nueva * 10.0) / 10.0;
    }
}
