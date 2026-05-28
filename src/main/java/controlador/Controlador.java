package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.ActuadorBase;
import modelo.GestorDatos;
import modelo.Registro;
import modelo.CasaInteligente;
import vista.PanelControl;

// Controlador principal. Une la vista con el modelo.
// Cuando el usuario pulsa un boton, aqui se decide que hacer.

public class Controlador implements ActionListener {

    private PanelControl vista;
    private CasaInteligente modelo;
    private GestorDatos persistencia;
    private Registro logger;

    public Controlador(PanelControl vista) {
        this.vista = vista;
        this.modelo = CasaInteligente.getInstancia();
        this.persistencia = new GestorDatos();
        this.logger = Registro.getInstancia();

        persistencia.cargar(modelo.getActuadores());

        vista.getBotonActualizar().addActionListener(this);
        vista.getBotonAplicarReglas().addActionListener(this);
        vista.getBotonGuardar().addActionListener(this);

        vista.refrescar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object origen = e.getSource();

        if (origen == vista.getBotonActualizar()) {
            modelo.actualizarSensores();
            vista.anadirLinea("Lectura de sensores completada.");
            vista.refrescar();

        } else if (origen == vista.getBotonAplicarReglas()) {
            String[] antes = new String[modelo.getActuadores().size()];
            for (int i = 0; i < modelo.getActuadores().size(); i++) {
                antes[i] = modelo.getActuadores().get(i).getEstadoActual(); // estado previo
            }

            modelo.aplicarReglas();

            // comparamos antes y despues para registrar solo los que cambiaron
            for (int i = 0; i < modelo.getActuadores().size(); i++) {
                ActuadorBase a = modelo.getActuadores().get(i);
                if (!antes[i].equals(a.getEstadoActual())) {
                    logger.registrarCambio(a.getID(), a.getEstadoActual());
                }
            }

            vista.anadirLinea("Reglas ejecutadas correctamente.");
            vista.refrescar();

        } else if (origen == vista.getBotonGuardar()) {
            persistencia.guardar(modelo.getActuadores());
            vista.anadirLinea("Estado guardado en fichero.");
        }
    }
}
