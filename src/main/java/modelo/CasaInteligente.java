package modelo;

import java.util.ArrayList;
import java.util.List;

// Modelo principal. Solo puede haber una instancia de la casa en toda la app.
public class CasaInteligente {

    private static CasaInteligente instancia;

    private List<SensorBase> sensores;
    private List<ActuadorBase> actuadores;
    private List<ReglaCasa> reglasActivas;

    private CasaInteligente() {
        sensores = new ArrayList<>();
        actuadores = new ArrayList<>();
        reglasActivas = new ArrayList<>();
        inicializarDispositivos();
    }

    public static CasaInteligente getInstancia() {
        if (instancia == null) {
            instancia = new CasaInteligente();
        }
        return instancia;
    }

    private void inicializarDispositivos() {
        sensores.add(new Termometro());
        sensores.add(new SensorLuminosidad());
        sensores.add(new DetectorMovimiento());
        // sensor añadido como ampliacion
        sensores.add(new Higrometro());

        actuadores.add(new Bombilla());
        actuadores.add(new Ventilador());
        // actuador añadido como ampliacion
        actuadores.add(new Enchufe());

        reglasActivas.add(new ReglaVentilacion());
        reglasActivas.add(new ReglaLuz());
    }

    public void actualizarSensores() {
        for (int i = 0; i < sensores.size(); i++) {
            sensores.get(i).actualizarValor();
        }
    }

    public void aplicarReglas() {
        for (int i = 0; i < reglasActivas.size(); i++) {
            reglasActivas.get(i).aplicar(sensores, actuadores);
        }
    }

    public List<SensorBase> getSensores() { return sensores; }
    public List<ActuadorBase> getActuadores() { return actuadores; }
    public List<ReglaCasa> getReglas() { return reglasActivas; }
}
