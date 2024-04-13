
public class ListaDoble<T extends Comparable<T>> {
    private Nodo<T> head;
    private Nodo<T> tail;
    private int size;

    private static <T extends Comparable<T>> ListaDoble<T>[] createArray() {
        @SuppressWarnings("unchecked")
        ListaDoble<T>[] array = (ListaDoble<T>[]) new ListaDoble[2];
        return array;
    }
    //Método de tiempo constante O(1)

    public ListaDoble<T>[] split() {
        ListaDoble<T>[] listas = createArray(); //t1f1 = 1 (tiempo constante)
        listas[0] = new ListaDoble<>(); //t2f2 = 1 (tiempo constante)
        listas[1] = new ListaDoble<>(); //t3f3 = 1 (tiempo constante)

        Nodo<T> lento = head; //t4f4 = 1 (tiempo constante)
        Nodo<T> rapido = head.next; //t5f5 = 1 (tiempo constante)

        while (rapido != null) { //t6f6 = n/2 + 1 = n/2 (tiempo O(n))
            rapido = rapido.next; //t7f7 = n/2 (tiempo constante)
            if (rapido != null) { //t8f8 = n/2 (tiempo constante)
                lento = lento.next; //t9f9 = n/2 (tiempo constante)
                rapido = rapido.next; //t10f10 = n/2 (tiempo constante)
            }
        }

        listas[0].head = head; //t11f11 = 1 (tiempo constante)
        listas[1].head = lento.next; //t12f12 = 1 (tiempo constante)
        lento.next = null; //t13f13 = 1 (tiempo constante)

        return listas; //t14f14 = 1 (tiempo constante)
    }

    public void insert(T data) {
        Nodo<T> newNode = new Nodo<>(data); //t1f1 = 1 (tiempo constante)

        if (head == null) { //t2f2 = 1 (tiempo constante)
            head = newNode; //t3f3 = 1 (tiempo constante)
            tail = newNode; //t4f4 = 1 (tiempo constante)
        } else {
            Nodo<T> current = tail; //t5f5 = 1 (tiempo constante)
            current.next = newNode; //t6f6 = 1 (tiempo constante)
            newNode.prev = current; //t7f7 = 1 (tiempo constante)
            tail = newNode; //t8f8 = 1 (tiempo constante)
        }
        size++; //t9f9 = 1 (tiempo constante)
    }
    //Método de tiempo constante O(1)

    public static <T extends Comparable<T>> ListaDoble<T> merge(ListaDoble<T> l1, ListaDoble<T> l2) {
        ListaDoble<T> mergedList = new ListaDoble<>(); //t1f1 = 1 (tiempo constante)
        Nodo<T> actual1 = l1.head; //t2f2 = 1 (tiempo constante)
        Nodo<T> actual2 = l2.head; //t3f3 = 1 (tiempo constante)

        while (actual1 != null && actual2 != null) { //t4f4 = n-1 (tiempo O(n))
            if (actual1.data.compareTo(actual2.data) <= 0) { //t5f5 = n-1 (tiempo O(n))
                mergedList.insert(actual1.data); //t6f6 = n (tiempo O(n))
                actual1 = actual1.next; //t7f7 = n (tiempo constante)
            } else {
                mergedList.insert(actual2.data); //t8f8 = n (tiempo O(n))
                actual2 = actual2.next; //t9f9 = n (tiempo constante)
            }
        }

        while (actual1 != null) { //t10f10 = n/2,(tiempo O(n))
            mergedList.insert(actual1.data); //t11f11 = n/2 (tiempo O(n))
            actual1 = actual1.next; //t12f12 = n/2 (tiempo constante)
        }

        while (actual2 != null) { //t13f13 = n/2, (tiempo O(n))
            mergedList.insert(actual2.data); //t14f14 = n/2 (tiempo O(n))
            actual2 = actual2.next; //t15f15 = n/2 (tiempo constante)
        }

        return mergedList; //t16f16 = 1 (tiempo constante)
    }
    //Método de complejidad de tiempo O(n)

    public static <T extends Comparable<T>> void mergeSort(ListaDoble<T> list) {
        if (list.head == null || list.head.next == null) { //t1f1 = 1 (tiempo constante)
            return; //t2f2 = 1 (tiempo constante)
        }

        ListaDoble<T>[] splitLists = list.split(); //t3f3 = n (tiempo O(n))
        mergeSort(splitLists[0]); //T(n/2) (caso recursivo) es el tamaño de cada subproblema
        mergeSort(splitLists[1]); //T(n/2) (caso recursivo)
        //Cantidad de llamados recursivos = 2

        list.head = merge(splitLists[0], splitLists[1]).head; //t4f4 = n (tiempo O(n))
    }
    //Por la fórmula del teorema Maestro:
    //T(n) = a * T(n/b) + f(n)
    //     = 2 * T(n/2) + O(n)
    /* Se usa el caso #2 del Teorema Maestro:
    f(n) = Θ(n^(log_b(a)))
    En este caso, log_2(2) = 1, por lo que n^(log_2(2)) = n^1 = n
    Como f(n) = O(n), este caso se cumple.
    Entonces tenemos: T(n) = Θ(n * log n)
     */

    public void printList() {
        Nodo<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}