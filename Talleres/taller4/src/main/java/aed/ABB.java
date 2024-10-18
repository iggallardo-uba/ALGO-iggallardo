package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo root;

    private class Nodo {
        T valor;
        Nodo izquierdo;
        Nodo derecho;
        Nodo padre;

        public Nodo(T valor, Nodo padre) {
            this.valor = valor; 
            this.izquierdo = null;
            this.derecho = null;
            this.padre = padre;
        }
    }

    public ABB() {
        root = null;
    }

    public int cardinal() {
        return cardinalNodo(root);
    }

    private int cardinalNodo(Nodo root){
        if(root == null) return 0;
        return 1 + cardinalNodo(root.izquierdo) + cardinalNodo(root.derecho);

    }

    public T minimo(){
        return minimoNodo(root);
    }

    private T minimoNodo(Nodo root){
        if (root.izquierdo == null) return root.valor;
        
        return minimoNodo(root.izquierdo);
    }

    public T maximo(){
        return maximoNodo(root);
    }

    private T maximoNodo(Nodo root){
        if (root.derecho == null) return root.valor;
        
        return maximoNodo(root.derecho);
    }

    public void insertar(T elem){
        root = insertNodo(elem, root, null);
    }

    private Nodo insertNodo(T elem, Nodo root, Nodo padre){
        if(root == null) return new Nodo(elem, padre);

        int comparacion = root.valor.compareTo(elem);

        if (comparacion > 0) root.izquierdo = insertNodo(elem, root.izquierdo, root);
        if (comparacion < 0) root.derecho = insertNodo(elem, root.derecho, root);

        return root;
    }

    public boolean pertenece(T elem){
        return perteneceNodo(elem, root);
    }

    private boolean perteneceNodo(T elem, Nodo root){
        if(root == null) return false;

        int comparacion = root.valor.compareTo(elem);

        if (comparacion > 0) return perteneceNodo(elem, root.izquierdo);
        if (comparacion < 0) return perteneceNodo(elem, root.derecho);
    
        return true;
    }

    public void eliminar (T elem){
        root = eliminarNodo(elem, root);
    }

    private Nodo eliminarNodo(T elem, Nodo root){
        if (root == null) return root;
        
        if (root.valor != elem){
            int comparacion = root.valor.compareTo(elem);

            if (comparacion > 0) root.izquierdo = eliminarNodo(elem, root.izquierdo);
            if (comparacion < 0) root.derecho = eliminarNodo(elem, root.derecho);
        
        } else{
            if(root.derecho == null && root.izquierdo == null) return null;
            //Cuidado estos casos y sus padres
            else if(root.derecho == null && root.izquierdo != null) {
                root.izquierdo.padre = root.padre;

                return root.izquierdo;
            }else if(root.derecho != null && root.izquierdo == null) {
                root.derecho.padre = root.padre;
                
                return root.derecho;
            }else if(root.derecho != null && root.izquierdo != null){
                T maximo = maximoNodo(root.izquierdo);

                root.valor = maximo;

                root.izquierdo = eliminarNodo(maximo, root.izquierdo);
            }
        }

        return root;
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    //Minimo Nodo 
    private Nodo minimoNodoIt(Nodo root){
        if (root.izquierdo == null) return root;
        
        return minimoNodoIt(root.izquierdo);
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo nodoActual;
        private T valorActual;

        public ABB_Iterador(){
            nodoActual = minimoNodoIt(root);
            valorActual = root.valor;
        }

        public boolean haySiguiente() {  
            valorActual = nodoActual.valor;
            
            if(nodoActual.derecho != null){
                return true;
            }
            if(nodoActual.padre != null){
                nodoActual = nodoActual.padre;

                if(valorActual.compareTo(nodoActual.valor) > 1) return true;
            }

            return false;
        }
    
        public T siguiente() {
            haySiguiente();

            return valorActual;
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
