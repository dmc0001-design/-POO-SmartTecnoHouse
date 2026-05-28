package modelo;

// Clase base para los actuadores. Cada subclase define sus propias acciones.
public abstract class ActuadorBase implements Dispositivo {

    protected String id;
    protected String nombre;
    protected String estado;

    public ActuadorBase(String id, String nombre, String estadoInicial) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estadoInicial;
    }

    public abstract void ejecutarAccion(String accion);

    public abstract String[] getAccionesPosibles();

    // Necesario para que la persistencia pueda restaurar estados al cargar
    public void setEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public String getID() { return id; }

    @Override
    public String getNombre() { return nombre; }

    @Override
    public String getEstadoActual() { return estado; }
}
