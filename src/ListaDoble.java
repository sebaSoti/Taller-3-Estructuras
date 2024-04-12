
public class ListaDoble<T extends Comparable<T>> {
    private Nodo<T> head;
    private Nodo<T> tail;
    private int size;

    private static <T extends Comparable<T>> ListaDoble<T>[] createArray() { //Se le ingresa parámetro de tamaño n?
        @SuppressWarnings("unchecked")
        ListaDoble<T>[] array = (ListaDoble<T>[]) new ListaDoble[2];
        return array;
    }
    //Método de tiempo constante O(1)

    public ListaDoble<T>[] split() {
        ListaDoble<T>[] lists = createArray(); //t1f1 = 1 (tiempo constante)
        lists[0] = new ListaDoble<>(); //t2f2 = 1 (tiempo constante)
        lists[1] = new ListaDoble<>(); //t3f3 = 1 (tiempo constante)

        Nodo<T> slow = head; //t4f4 = 1 (tiempo constante)
        Nodo<T> fast = head.next; //t5f5 = 1 (tiempo constante)

        while (fast != null) { //t6f6 = n/2 + 1, t7f7 = n/2 (tiempo O(n))
            fast = fast.next; //t8f8 = n/2 (tiempo constante)
            if (fast != null) { //t9f9 = n/2 (tiempo constante)
                slow = slow.next; //t10f10 = n/2 (tiempo constante)
                fast = fast.next; //t11f11 = n/2 (tiempo constante)
            }
        }

        lists[0].head = head; //t12f12 = 1 (tiempo constante)
        lists[1].head = slow.next; //t13f13 = 1 (tiempo constante)
        slow.next = null; //t14f14 = 1 (tiempo constante)

        return lists; //t15f15 = 1 (tiempo constante)
    }
    //Complejidad O(n)
    /*public void insert(T data) {
        Nodo<T> newNode = new Nodo<>(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            Nodo<T> current = head;

            if (data.compareTo((T) current.data) < 0) {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else {
                while (current.next != null && data.compareTo((T) current.next.data) > 0) {
                    current = current.next;
                }

                if (current.next == null) {
                    current.next = newNode;
                    newNode.prev = current;
                    tail = newNode;
                } else {
                    newNode.next = current.next;
                    newNode.prev = current;
                    current.next.prev = newNode;
                    current.next = newNode;
                }
            }
        }
    }
    */
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

        while (actual1 != null && actual2 != null) { //t4f4 = n1 + n2, t5f5 = n1 + n2 - 1 (tiempo O(n1 + n2))
            if (actual1.data.compareTo(actual2.data) <= 0) { //t6f6 = n1 + n2 - 1 (tiempo O(n1 + n2))
                mergedList.insert(actual1.data); //t7f7 = n1 (tiempo O(n1))
                actual1 = actual1.next; //t8f8 = n1 (tiempo constante)
            } else {
                mergedList.insert(actual2.data); //t9f9 = n2 (tiempo O(n2))
                actual2 = actual2.next; //t10f10 = n2 (tiempo constante)
            }
        }

        while (actual1 != null) { //t11f11 = n1, t12f12 = n1 (tiempo O(n1))
            mergedList.insert(actual1.data); //t13f13 = n1 (tiempo O(n1))
            actual1 = actual1.next; //t14f14 = n1 (tiempo constante)
        }

        while (actual2 != null) { //t15f15 = n2, t16f16 = n2 (tiempo O(n2))
            mergedList.insert(actual2.data); //t17f17 = n2 (tiempo O(n2))
            actual2 = actual2.next; //t18f18 = n2 (tiempo constante)
        }

        return mergedList; //t19f19 = 1 (tiempo constante)
    }
    //Método de complejidad de tiempo O(n + m)?, donde n y m son los tamaños de las listas
    //Realmente debe de ser de complejidad O(n) el merge

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