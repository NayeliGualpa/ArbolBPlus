import javax.swing.JOptionPane;

public class App {

    public static void main(String[] args) {
        BTreePlus arbol = new BTreePlus(2); // Árbol B+ de orden 2
        int opcion = 0;

        do {
            String menu = "🌳 MENÚ DEL ÁRBOL B+\n"
                    + "1. Insertar clave\n"
                    + "2. Visualizador de Arbol B+\n"
                    + "3. Salir";

            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menu, "Árbol B+", JOptionPane.PLAIN_MESSAGE));

                switch (opcion) {
                    case 1:
                        int claveInsertar = Integer.parseInt(
                                JOptionPane.showInputDialog("🔢 Ingrese clave a insertar:"));
                        arbol.insertar(claveInsertar);
                        JOptionPane.showMessageDialog(null, " Clave insertada.");
                        break;
                     case 2:
                        // Visualizador de Arbol B+
                        VisualizadorBPlus.visualizar(arbol.raiz); // Metodo que permite mostrar visualmente el Arbol
                        break;    

                    case 3:
                        JOptionPane.showMessageDialog(null, " Gracias por usar el Árbol B+");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, " Opción no válida.");
                        break;
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, " Entrada no válida. Ingrese un número.");
            }

        } while (opcion != 3);
    }
}
