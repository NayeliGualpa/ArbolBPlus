import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class VisualizadorBPlus extends JPanel {
    private final Nodo raiz;
    private final int radio = 30;
    private final int distY = 80;

    public VisualizadorBPlus(Nodo raiz) {
        this.raiz = raiz;
        setPreferredSize(new Dimension(900, 700));
        setBackground(Color.WHITE);
    }

    public static void visualizar(Nodo raiz) {
        JFrame frame = new JFrame("Visualización del Árbol B+");
        VisualizadorBPlus panel = new VisualizadorBPlus(raiz);
        frame.add(new JScrollPane(panel));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (raiz != null) {
            dibujarNodo(g, raiz, getWidth() / 2, 40, getWidth() / 4);
        }
    }

    private void dibujarNodo(Graphics g, Nodo nodo, int x, int y, int distX) {
        if (nodo == null) return;

        int n = nodo.cantidad;
        int totalWidth = n * radio;
        int startX = x - totalWidth / 2;

        if (!nodo.esHoja) {
            for (int i = 0; i <= n; i++) {
                int childX = (n == 0) ? x : x - distX + i * (2 * distX / n);
                int childY = y + distY;

                g.setColor(Color.BLACK);
                g.drawLine(x, y + radio, childX, childY);

                dibujarNodo(g, nodo.hijos[i], childX, childY, distX / 2);
            }
        }

        for (int i = 0; i < n; i++) {
            int rectX = startX + i * radio;
            int rectY = y;

            g.setColor(nodo.esHoja ? new Color(144, 238, 144) : new Color(173, 216, 230));
            g.fillRect(rectX, rectY, radio, radio);

            g.setColor(Color.BLACK);
            g.drawRect(rectX, rectY, radio, radio);

            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(nodo.claves[i]), rectX + radio / 4, rectY + (int)(radio * 0.65));
        }

        if (nodo.esHoja && nodo.siguienteHoja != null) {
            int flechaXInicio = startX + n * radio + 10;
            int flechaY = y + radio / 2;

            g.setColor(Color.RED);
            g.drawLine(flechaXInicio, flechaY, flechaXInicio + 40, flechaY);
            g.drawLine(flechaXInicio + 40, flechaY, flechaXInicio + 35, flechaY - 5);
            g.drawLine(flechaXInicio + 40, flechaY, flechaXInicio + 35, flechaY + 5);
        }
    }
}
