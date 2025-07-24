ÁRBOLES MULTICAMINOS / ÁRBOL B+

INSERCIÓN / Un Árbol B+ de orden d:
Cada nodo interno (no hoja) puede tener entre d y 2d claves.
Cada hoja puede tener entre d y 2d claves.
Solo las hojas contienen los datos reales (o claves finales).
Los nodos internos solo almacenan las claves que guían la búsqueda (índices).
Las hojas están enlazadas entre sí, de izquierda a derecha, para facilitar recorrido secuencial.

1. Insertar como si fuera una lista ordenada
Empieza desde la raíz y baja recursivamente hasta una hoja.
Encuentra la posición correcta de la clave (manteniendo el orden).
Inserta la clave en la hoja, si hay espacio.

2. ¿Qué pasa si la hoja se llena? (más de 2d claves)
Dividir la hoja en dos nodos hoja:
La mitad izquierda queda en la hoja original.
La mitad derecha se pasa a una nueva hoja.
La clave más pequeña de la nueva hoja sube al nodo padre como índice.
Actualiza el enlace entre hojas (el puntero siguienteHoja).

3. ¿Qué pasa si el nodo interno se llena al insertar el índice?
Dividir el nodo interno igual que en un árbol B:
Se divide en dos partes, y la clave del medio sube al siguiente nivel.
Si se divide la raíz, se crea una nueva raíz.
