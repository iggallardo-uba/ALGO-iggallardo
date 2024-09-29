package aed;

public class Horario {
    private int hora;
    private int minutos;

    public Horario(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    public Horario(Horario horario) {
        this.hora = horario.hora();
        this.minutos = horario.minutos();
    }

    public int hora() {
        return hora;
    }

    public int minutos() {
        return minutos;
    }

    @Override public String toString() {
        return this.hora()+":"+this.minutos();   
    }

    @Override public boolean equals(Object otro) {
        //Chequeo base de si es null o dif clase
        boolean otroEsNull = (otro == null);

        boolean claseDistinta = otro.getClass() != this.getClass();

        if (otroEsNull || claseDistinta) {
			return false;
		}

        //Chequeo de la clase
        //  casting -> cambiar el tipo
		Horario otroHorario = (Horario) otro;


		// comparar item a ítem
		return this.hora() == otroHorario.hora() && this.minutos() == otroHorario.minutos();
    }

}
