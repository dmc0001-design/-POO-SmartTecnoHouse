package modelo;

// Clase base para los sensores. Cada subclase simula su propia lectura.
public abstract class SensorBase implements Dispositivo {

    protected String id;
    protected String nombre;
    protected String unidad;
    protected double ultimoValor;

    public SensorBase(String id, String nombre, String unidad) {
        this.id = id;
        this.nombre = nombre;
        this.unidad = unidad;
    }

    public abstract void actualizarValor();

    public double getValor() {
        return ultimoValor;
    }

    @Override
    public String getID() { return id; }

    @Override
    public String getNombre() { return nombre; }

    @Override
    public String getEstadoActual() {
        // Mostramos valor + unidad para que quede claro en la GUI
        return ultimoValor + " " + unidad;
    }
}
