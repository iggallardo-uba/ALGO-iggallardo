package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] listaRecordatorios;

    public ArregloRedimensionableDeRecordatorios() {
        listaRecordatorios = new Recordatorio[0];
    }

    public int longitud() {
        return this.listaRecordatorios.length;
    }

    public void agregarAtras(Recordatorio i) {
        Recordatorio newList[] = new Recordatorio[this.longitud()+1];

        for(int j = 0; j < this.longitud() ; j++) newList[j] = listaRecordatorios[j];

        System.err.println(this.longitud()+1);

        newList[this.longitud()] = i;

        listaRecordatorios = newList;
    }

    public Recordatorio obtener(int i) {
        return listaRecordatorios[i];
    }

    public void quitarAtras() {
        Recordatorio newList[] = new Recordatorio[this.longitud()-1];

        for(int j = 0; j < this.longitud()-1 ; j++) newList[j] = listaRecordatorios[j];

        listaRecordatorios = newList;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        Recordatorio newList[] = new Recordatorio[this.longitud()];

        for(int j = 0; j < this.longitud() ; j++){
            if(j == indice){
                newList[j] = valor;
            }else{
                newList[j] = listaRecordatorios[j];
            }  
        }
        listaRecordatorios = newList;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        Recordatorio newList[] = new Recordatorio[vector.longitud()];

        for(int j = 0; j < vector.longitud() ; j++) newList[j] = vector.obtener(j);

        listaRecordatorios = newList;
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        ArregloRedimensionableDeRecordatorios nuevoArreglo = new ArregloRedimensionableDeRecordatorios();

        for(int i = 0; i < this.longitud(); i++) nuevoArreglo.agregarAtras(this.obtener(i));

        return nuevoArreglo;
    }
}
