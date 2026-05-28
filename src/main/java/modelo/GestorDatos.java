package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// Guarda y carga el estado de los actuadores en un fichero JSON.
public class GestorDatos {

    private static final String FICHERO = "datos.json";

    public void guardar(List<ActuadorBase> actuadores) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"actuadores\": [\n");
        for (int i = 0; i < actuadores.size(); i++) {
            ActuadorBase a = actuadores.get(i);
            sb.append("    {\"id\": \"").append(a.getID()).append("\", ");
            sb.append("\"estado\": \"").append(a.getEstadoActual()).append("\"}");
            if (i < actuadores.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("  ]\n");
        sb.append("}\n");

        try {
            FileWriter fw = new FileWriter(FICHERO);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public void cargar(List<ActuadorBase> actuadores) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FICHERO));
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("\"id\"") && linea.contains("\"estado\"")) {
                    String id = extraerValor(linea, "id");
                    String estado = extraerValor(linea, "estado");
                    for (int i = 0; i < actuadores.size(); i++) {
                        if (actuadores.get(i).getID().equals(id)) {
                            actuadores.get(i).setEstado(estado);
                            break;
                        }
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Sin fichero previo, se usan valores por defecto.");
        }
    }

    // Extrae el valor de una clave dentro de una linea JSON simple
    private String extraerValor(String linea, String clave) {
        int pos = linea.indexOf(":", linea.indexOf("\"" + clave + "\""));
        int inicio = linea.indexOf("\"", pos) + 1;
        return linea.substring(inicio, linea.indexOf("\"", inicio));
    }
}
