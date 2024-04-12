/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Daniela
 */
public class Nodo<T extends Comparable<T>> {
    public T data;
    public Nodo<T> next;
    public Nodo<T> prev;

    public Nodo(T data) {
        this.data = data;
    }
}