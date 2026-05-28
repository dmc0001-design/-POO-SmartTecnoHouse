import javax.swing.SwingUtilities;
import controlador.Controlador;
import vista.PanelControl;

//Se utiliza para lanzar la GUI en el hilo de Swing.
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PanelControl v = new PanelControl();
                new Controlador(v);
                v.setVisible(true);
            }
        });
    }
}
