package vista;

import javax.swing.*;
import java.awt.*;

import modelo.ActuadorBase;
import modelo.SensorBase;
import modelo.CasaInteligente;

// Ventana principal de la aplicacion. Muestra los datos y los botones.
public class PanelControl extends JFrame {

    private JPanel panelSensores;
    private JPanel panelActuadores;
    private JButton botonActualizar;
    private JButton botonAplicarReglas;
    private JButton botonGuardar;
    private JTextArea areaLog;

    public PanelControl() {
        setTitle("Casa Inteligente - Control Central");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panelSensores = new JPanel();
        panelSensores.setBorder(BorderFactory.createTitledBorder("Estado sensores"));
        panelSensores.setLayout(new GridLayout(0, 1));

        panelActuadores = new JPanel();
        panelActuadores.setBorder(BorderFactory.createTitledBorder("Estado actuadores"));
        panelActuadores.setLayout(new GridLayout(0, 1));

        JPanel panelCentral = new JPanel(new GridLayout(1, 2));
        panelCentral.add(panelSensores);
        panelCentral.add(panelActuadores);

        botonActualizar = new JButton("Leer sensores");
        botonAplicarReglas = new JButton("Ejecutar reglas");
        botonGuardar = new JButton("Guardar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonActualizar);
        panelBotones.add(botonAplicarReglas);
        panelBotones.add(botonGuardar);

        areaLog = new JTextArea(6, 50);
        areaLog.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(areaLog);

        add(panelBotones, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(scrollLog, BorderLayout.SOUTH);
    }

    // Refresca los paneles a partir del estado actual del modelo
    public void refrescar() {
        CasaInteligente casa = CasaInteligente.getInstancia();

        panelSensores.removeAll();
        for (int i = 0; i < casa.getSensores().size(); i++) {
            SensorBase s = casa.getSensores().get(i);
            JLabel etiqueta = new JLabel(s.getNombre() + ": " + s.getEstadoActual());
            panelSensores.add(etiqueta);
        }

        panelActuadores.removeAll();
        for (int i = 0; i < casa.getActuadores().size(); i++) {
            ActuadorBase a = casa.getActuadores().get(i);
            JLabel etiqueta = new JLabel(a.getNombre() + ": " + a.getEstadoActual());
            panelActuadores.add(etiqueta);
        }

        panelSensores.revalidate();
        panelSensores.repaint();
        panelActuadores.revalidate();
        panelActuadores.repaint();
    }

    public void anadirLinea(String texto) {
        areaLog.append(texto + "\n");
    }

    public JButton getBotonActualizar() { return botonActualizar; }
    public JButton getBotonAplicarReglas() { return botonAplicarReglas; }
    public JButton getBotonGuardar() { return botonGuardar; }
}
