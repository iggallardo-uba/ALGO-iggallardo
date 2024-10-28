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
        
        int comparacion = root.valor.compareTo(elem);

        if (comparacion != 0){
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
        if (this.cardinal() == 0) return "{}";
        if (this.cardinal() == 1) return "{" + root.valor.toString() + "}";

        Iterador<T> iterador = this.iterador();

        String ABBstring = "{";

        for(int i = 0; i < this.cardinal()-1; i++)
            ABBstring = ABBstring + iterador.siguiente().toString() + ",";
        ABBstring = ABBstring + iterador.siguiente().toString() + "}";

        return ABBstring;
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
        }

        public boolean haySiguiente() {  
            return true;
        }

        public T siguiente() {
            valorActual = nodoActual.valor;

            nodoActual = siguienteRecursivo(nodoActual);

            return valorActual;
        }

        public Nodo siguienteRecursivo(Nodo root){
            if(root.derecho !=  null) return minimoNodoIt(root.derecho);
        
            while (root.padre != null && root == root.padre.derecho) {
                root = root.padre;
            }

            if (root.padre == null) return root;

            return root.padre;
        }

        /* 
        Fuck recursion, all my homies love iteration
        public boolean haySiguiente() {  
            if(nodoActual.derecho == null && nodoActual.izquierdo == null){
                if(nodoActual.padre == null) return false;
                else{
                    if(nodoActual.padre.valor.compareTo(valorActual) > 0){
                        nodoActual = nodoActual.padre;
                        if(nodoActual.padre == null) return true;
                        else return haySiguiente();
                    } else if (nodoActual.padre.valor.compareTo(valorActual) == 0) return true;
                    else{
                        nodoActual = nodoActual.padre;
                        return haySiguiente();
                    }      
                }
            }

            if(nodoActual.padre == null){
                if(nodoActual.derecho == null) return true;
                
                nodoActual = nodoActual.derecho;
                return haySiguiente();
            }

            if(nodoActual.izquierdo != null){
                if(nodoActual.izquierdo.valor.compareTo(valorActual) > 0){
                    nodoActual = nodoActual.izquierdo;
                    if(nodoActual.izquierdo == null) return true;
                    else return haySiguiente();
                }
            }

            if(nodoActual.derecho != null){
                if(nodoActual.valor.compareTo(valorActual) > 0){
                    return true;
                }
                
                if(nodoActual.derecho.valor.compareTo(valorActual) > 0){
                    nodoActual = nodoActual.derecho;
                    return haySiguiente();
                }
            } else {
                if(nodoActual.valor.compareTo(valorActual) > 0 && nodoActual.padre.valor.compareTo(valorActual) > 0){
                    return true;
                }
            }
            
            if(nodoActual.padre.valor.compareTo(valorActual) > 0){
                nodoActual = nodoActual.padre;
                return haySiguiente();
            }

            return false;
        }
    
        public T siguiente() {
            valorActual = nodoActual.valor;
            haySiguiente();
            
            return valorActual;
        }
    */
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }
}
