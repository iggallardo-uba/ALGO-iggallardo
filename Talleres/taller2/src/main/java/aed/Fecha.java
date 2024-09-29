package aed;

public class Fecha {
    private int dia;
    private int mes;

    public Fecha(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    public Fecha(Fecha fecha) {
        this.dia = fecha.dia();
        this.mes = fecha.mes();
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
    @Override public boolean equals(Object otro) {
        //Chequeo base de si es null o dif clase
        boolean otroEsNull = (otro == null);

        boolean claseDistinta = otro.getClass() != this.getClass();

        if (otroEsNull || claseDistinta) {
			return false;
		}

        //Chequeo de la clase
        //  casting -> cambiar el tipo
		Fecha otroFecha = (Fecha) otro;


		// comparar item a ítem
		return this.dia() == otroFecha.dia() && this.mes() == otroFecha.mes();
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
