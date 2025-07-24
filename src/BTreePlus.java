public class BTreePlus {
Nodo raiz;
    int orden;

    // Constructor que inicializa el árbol con un orden específico.
    public BTreePlus(int orden) {
        this.raiz = null;
        this.orden = orden;
    }

    // Método para insertar una clave en el árbol
    public void insertar(int clave) {
        if (raiz == null) {
            raiz = new Nodo(orden, true); // Si el árbol está vacío, se crea una nueva raíz.
            raiz.claves[0] = clave;
            raiz.cantidad = 1;
        } else {
            Nodo nodo = raiz;
            Nodo padre = null;

            // Buscar la hoja donde se debe insertar la clave
            while (!nodo.esHoja) {
                padre = nodo;
                int i = 0;
                while (i < nodo.cantidad && clave > nodo.claves[i]) {
                    i++;
                }
                nodo = nodo.hijos[i];
            }

            // Si la pagina tiene espacio, se inserta la clave
            if (nodo.cantidad < 2 * orden) {  // 4 claves
                int i = 0;
                while (i < nodo.cantidad && clave > nodo.claves[i]) {
                    i++;
                }
                for (int j = nodo.cantidad - 1; j >= i; j--) {
                    nodo.claves[j + 1] = nodo.claves[j];
                }
                nodo.claves[i] = clave;
                nodo.cantidad++;
            } else {
                // Si la pagína está llena dividirla
                Nodo nuevo = new Nodo(orden, true);
                int[] tempClaves = new int[2 * orden + 1];  // Almacenar temporalmente las claves
                System.arraycopy(nodo.claves, 0, tempClaves, 0, 2 * orden);  // Copiar las claves actuales

                int i = 0;
                while (i < 2 * orden && clave > tempClaves[i]) {
                    i++;
                }

                // Mover las claves para hacer espacio para la nueva clave
                for (int j = 2 * orden ; j > i; j--) {
                    tempClaves[j] = tempClaves[j - 1];
                }
                tempClaves[i] = clave;

                int claveCentral = tempClaves[orden];

                // Divide el nodo en dos y actualiza sus claves
                nodo.cantidad = orden;
                nuevo.cantidad = orden + 1;

                System.arraycopy(tempClaves, 0, nodo.claves, 0, orden);
                System.arraycopy(tempClaves, orden, nuevo.claves, 0, orden+1);

                if (nodo == raiz) {
                    // Si ls pagína actual es la raíz crear una nueva raíz
                    Nodo nuevaRaiz = new Nodo(orden, false);
                    nuevaRaiz.claves[0] = claveCentral;
                    nuevaRaiz.cantidad = 1;
                    nuevaRaiz.hijos[0] = nodo;
                    nuevaRaiz.hijos[1] = nuevo;
                    raiz = nuevaRaiz;
                } else {
                    // Si no es la raíz, inserta la clave
                    insertarEnPadre(padre, claveCentral, nuevo);
                }
            }
        }
    }


    // Método para insertar una clave en el nodo padre
    private void insertarEnPadre(Nodo padre, int clavePromovida, Nodo hijoDerecho) {
        // Buscar la posición en el nodo padre para insertar la clave promovida
        int i = 0;
        while (i < padre.cantidad && clavePromovida > padre.claves[i]) {
            i++;
        }

        // Mover las claves para hacer espacio
        for (int j = padre.cantidad - 1; j >= i; j--) {
            padre.claves[j + 1] = padre.claves[j];
            padre.hijos[j + 2] = padre.hijos[j + 1];
        }

        // Insertar la clave promovida
        padre.claves[i] = clavePromovida;
        padre.hijos[i + 1] = hijoDerecho;
        padre.cantidad++;

        // Si la pagína está llena dividirla
        if (padre.cantidad == 2 * orden) {
            Nodo nuevoPadre = new Nodo(orden, false);
            int[] tempClaves = new int[2 * orden];
            Nodo[] tempHijos = new Nodo[2 * orden + 1];

            System.arraycopy(padre.claves, 0, tempClaves, 0, padre.cantidad);
            System.arraycopy(padre.hijos, 0, tempHijos, 0, padre.cantidad + 1);

            // Dividir el padre
            int medio = padre.cantidad / 2;
            nuevoPadre.cantidad = padre.cantidad - medio - 1;

            System.arraycopy(tempClaves, medio + 1, nuevoPadre.claves, 0, nuevoPadre.cantidad);
            System.arraycopy(tempHijos, medio + 1, nuevoPadre.hijos, 0, nuevoPadre.cantidad + 1);

            padre.cantidad = medio;
            if (padre == raiz) {
                Nodo nuevaRaiz = new Nodo(orden, false);
                nuevaRaiz.claves[0] = tempClaves[medio];
                nuevaRaiz.cantidad = 1;
                nuevaRaiz.hijos[0] = padre;
                nuevaRaiz.hijos[1] = nuevoPadre;
                raiz = nuevaRaiz;
            } else {
                insertarEnPadre(padre.hijos[medio], tempClaves[medio], nuevoPadre);
            }
        }
    }
 }