package modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Guarda en un fichero los cambios de estado de los actuadores.
public class Registro {

    private static Registro instancia;
    private static final String FICHERO_LOG = "actuators.log";
    private SimpleDateFormat formatoFecha;

    private Registro() {
        formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static Registro getInstancia() {
        if (instancia == null) {
            instancia = new Registro();
        }
        return instancia;
    }

    public void registrarCambio(String idActuador, String nuevoEstado) {
        String marca = formatoFecha.format(new Date());
        String linea = marca + " - " + idActuador + " -> " + nuevoEstado + "\n";

        try {
            FileWriter fw = new FileWriter(FICHERO_LOG, true); // true = no sobreescribir
            fw.write(linea);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al escribir en el log: " + e.getMessage());
        }
    }
}
