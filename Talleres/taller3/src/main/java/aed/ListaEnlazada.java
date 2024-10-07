package aed;

import java.util.*;

import javax.swing.text.html.StyleSheet.ListPainter;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo primero;
    private Nodo ultimo;

    private class Nodo {
        T valor;
        Nodo siguiente;
        Nodo anterior;

        public Nodo(T valor) {
            this.valor = valor; 
            this.siguiente = null;
            this.anterior = null;
        }
    }

    public ListaEnlazada() {
        this.primero = null;
        this.ultimo = null;
    }

    public int longitud() {
        //Caso 0 y Caso 1
        if(primero == null && ultimo == null) return 0;
        if(primero == ultimo) return 1;

        //Resto de casos
        int longitud = 0;
        Nodo nodo = primero;

        while(nodo != null) {nodo = nodo.siguiente; longitud += 1; }

        return longitud;
    }

    public void agregarAdelante(T elem) {
        //Nodo a agregar
        Nodo nodo = new Nodo(elem);

        //Caso longitud es 0
        if(primero == null) {
            primero = nodo;
            ultimo = nodo;
        } else {
            nodo.siguiente = primero;
            primero.anterior = nodo;

            primero = nodo;
        }
    }

    public void agregarAtras(T elem) {
        Nodo nodo = new Nodo(elem);

        if(ultimo == null) {
            primero = new Nodo(elem);
            //asegurar misma memoria
            ultimo = primero;
        } else {
            ultimo.siguiente = nodo;
            nodo.anterior = ultimo;
            
            ultimo = nodo;
        }
    }

    public T obtener(int i) {
        Nodo nodo = primero;

        for(int j = 0; j < i; j++) { 
            nodo = nodo.siguiente; 
        }

        return nodo.valor;
    }

    public Nodo obtenerNodo(int i) {
        Nodo nodo = primero;

        for(int j = 0; j < i; j++) { 
            nodo = nodo.siguiente; 
        }

        return nodo;
    }

    public void eliminar(int i) {
        Nodo eliminar = obtenerNodo(i);
        Nodo anterior =  eliminar.anterior;
        Nodo siguiente = eliminar.siguiente;
        
        if(primero == ultimo) {
            primero = null;
            ultimo = null;
        } else if(eliminar == primero){
            primero = siguiente;
            siguiente.anterior = null;
        } else if(eliminar == ultimo){
            ultimo = anterior;
            anterior.siguiente = null;
        } else {
            anterior.siguiente = siguiente;
            siguiente.anterior = anterior;
        }

        eliminar = null;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo cambiar = obtenerNodo(indice);

        cambiar.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        if(lista.longitud() == 0){
            primero = null;
            ultimo = null;
        } else if (lista.longitud() == 1){
            primero = new Nodo(lista.obtener(0));
            ultimo = primero;
        } else {
            Nodo nodo_anterior = new Nodo(lista.obtener(0));
            primero = nodo_anterior;

            for(int i = 1; i < lista.longitud(); i++){
                Nodo nodo = new Nodo(lista.obtener(i));

                nodo_anterior.siguiente = nodo;
                nodo.anterior = nodo_anterior;

                nodo_anterior = nodo;

                if(i == lista.longitud() - 1) { ultimo = nodo; }
            } 
        }
    }
    
    @Override public String toString() {
        String string = "[";

        for(int i = 0; i < longitud(); i++){
            if(i != longitud()-1){
                string = string + obtener(i).toString() + ", "; 
            } else {
                string = string + obtener(i).toString(); 
            }
        }

        string += "]";
        return string;
    }

    private class ListaIterador implements Iterador<T> {
        int indice;

        ListaIterador(){
            this.indice = 0;
        }

        public boolean haySiguiente() {
	        return indice < longitud();
        }
        
        public boolean hayAnterior() {
	        return indice > 0;
        }

        public T siguiente() {
	        int i = indice;
            indice = indice + 1;

            return obtener(i);
        }
        

        public T anterior() {
	        int i = indice - 1;
            indice = indice - 1;

            return obtener(i);
        }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }
}
