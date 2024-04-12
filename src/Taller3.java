import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 *
 * @author Daniela
 */
public class Taller3 {

    public static void main(String[] args) {
        ListaDoble<Integer> list = new ListaDoble<>();
        list.insert(5);
        list.insert(3);
        list.insert(8);
        list.insert(1);
        list.insert(6);
        list.insert(9);
        list.insert(2);
        list.insert(7);
        list.insert(4);

        System.out.println("Lista original:");
        list.printList();

        ListaDoble.mergeSort(list); // call the mergeSort method on the ListaDoble class

        System.out.println("\nLista ordenada:");
        list.printList();
        //
        ListaDoble<Integer> list1 = new ListaDoble<>();
        list1.insert(1);
        list1.insert(3);
        list1.insert(5);

        ListaDoble<Integer> list2 = new ListaDoble<>();
        list2.insert(2);
        list2.insert(4);
        list2.insert(6);

        ListaDoble<Integer> mergedList = ListaDoble.merge(list1, list2);

        System.out.println("\nLista con Merge:");
        mergedList.printList();

        //
        ListaDoble<Integer>[] splitLists = list.split();

        System.out.println("\nPrimera mitad de la lista");
        splitLists[0].printList();

        System.out.println("\nSegunda mitad de la lista");
        splitLists[1].printList();

        // arrayFromInput();
        int n = 2000; // Número de elementos a generar y ordenar
        long startTime, endTime, duration;

        // Generar lista de personas
        List<Person> personas = GeneradorADTs.generar(n);
        ListaDoble<Person> listaDoble = new ListaDoble<>();
        for (Person p : personas) {
            listaDoble.insert(p);
        }

        // Medir tiempo de ordenamiento
        startTime = System.nanoTime();
        ListaDoble.mergeSort(listaDoble);
        endTime = System.nanoTime();

        duration = (endTime - startTime) / 1000000; // Duración en milisegundos
        double durationInSeconds = duration / 1000.0; // Duración en segundos

        System.out.println("Se ordenaron " + n + " elementos en " + durationInSeconds + " segundos.");
    }

}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
