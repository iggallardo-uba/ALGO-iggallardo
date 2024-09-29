package aed;

public class Fecha {
    private int dia;
    private int mes;

    public Fecha(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    public Fecha(Fecha fecha) {
        dia = fecha.dia();
        mes = fecha.mes();
    }

    public Integer dia() {
        return dia;
    }

    public Integer mes() {
        return mes;
    }

    @Override public String toString() {
        return dia + "/" + mes;
    }

    // Chequear si esto ta bien (Creo que no)
    @Override public boolean equals(Object otra) {
        return (this.toString() == otra.toString());
    }

    public void incrementarDia() {
        dia += 1;

        if(this.diasEnMes(mes) < dia){
            mes += 1;
            dia = 1;
        }

        if(mes >12) mes = 1;
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
