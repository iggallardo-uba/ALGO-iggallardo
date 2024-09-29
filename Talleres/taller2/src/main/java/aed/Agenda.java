package aed;

public class Agenda {
    private Fecha fechaActual;
    private ArregloRedimensionableDeRecordatorios recordatorios;


    public Agenda(Fecha fechaActual) {
        this.fechaActual = new Fecha(fechaActual);
        this.recordatorios = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        this.recordatorios.agregarAtras(recordatorio);
    }

    @Override public String toString() {
        String stringAgenda = fechaActual.toString() + "\n=====\n";

        for(int i = 0; i < this.recordatorios.longitud(); i++){
            if(this.recordatorios.obtener(i).fecha().equals(this.fechaActual)){
                stringAgenda += this.recordatorios.obtener(i).toString() + "\n";
            }
        }

        return stringAgenda;
    }

    public void incrementarDia() {
        this.fechaActual.incrementarDia();
    }

    public Fecha fechaActual() {
        return new Fecha(this.fechaActual);
    }

}
