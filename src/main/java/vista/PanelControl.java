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
        setTitle("SmartTecnoHouse - Panel de Control");
        setSize(820, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));
        getContentPane().setBackground(new Color(232, 245, 240));

        // Sidebar izquierdo con botones en columna
        botonActualizar    = new JButton("Leer sensores");
        botonAplicarReglas = new JButton("Ejecutar reglas");
        botonGuardar       = new JButton("Guardar");

        JPanel sidebar = new JPanel(new GridLayout(3, 1, 0, 10));
        sidebar.setBackground(new Color(180, 215, 200));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 12, 20, 12));
        sidebar.add(botonActualizar);
        sidebar.add(botonAplicarReglas);
        sidebar.add(botonGuardar);

        // Paneles de datos apilados verticalmente
        panelSensores = new JPanel(new GridLayout(0, 1));
        panelSensores.setBorder(BorderFactory.createTitledBorder("Sensores"));
        panelSensores.setBackground(new Color(245, 252, 249));

        panelActuadores = new JPanel(new GridLayout(0, 1));
        panelActuadores.setBorder(BorderFactory.createTitledBorder("Actuadores"));
        panelActuadores.setBackground(new Color(245, 252, 249));

        JPanel panelDatos = new JPanel(new GridLayout(2, 1, 0, 8));
        panelDatos.setBackground(new Color(232, 245, 240));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(8, 4, 8, 8));
        panelDatos.add(panelSensores);
        panelDatos.add(panelActuadores);

        // Log inferior
        areaLog = new JTextArea(4, 40);
        areaLog.setEditable(false);
        areaLog.setBackground(new Color(245, 252, 249));
        JScrollPane scrollLog = new JScrollPane(areaLog);
        scrollLog.setBorder(BorderFactory.createTitledBorder("Log"));

        add(sidebar,    BorderLayout.WEST);
        add(panelDatos, BorderLayout.CENTER);
        add(scrollLog,  BorderLayout.SOUTH);
    }

    // Refresca los paneles a partir del estado actual del modelo
    public void refrescar() {
        CasaInteligente casa = CasaInteligente.getInstancia();

        panelSensores.removeAll();
        for (int i = 0; i < casa.getSensores().size(); i++) {
            SensorBase s = casa.getSensores().get(i);
            JLabel etiqueta = new JLabel("  " + s.getNombre() + ": " + s.getEstadoActual());

            panelSensores.add(etiqueta);
        }

        panelActuadores.removeAll();
        for (int i = 0; i < casa.getActuadores().size(); i++) {
            ActuadorBase a = casa.getActuadores().get(i);
            JLabel etiqueta = new JLabel("  " + a.getNombre() + ": " + a.getEstadoActual());

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
