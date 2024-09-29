package aed;

public class Recordatorio {
    private String mensaje;
    private Fecha fecha;
    private Horario horario;


    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.mensaje = mensaje;
        this.fecha = new Fecha(fecha);
        this.horario = new Horario(horario);
    }

    public Horario horario() {
        return new Horario(this.horario);
    }

    public Fecha fecha() {
        return new Fecha(this.fecha);
    }

    public String mensaje() {
        return mensaje;
    }

    @Override public String toString() {
        return this.mensaje() + " @ " + this.fecha().toString() + " " + this.horario().toString();
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
                Recordatorio otroRecordatorio = (Recordatorio) otro;
        
        
                // comparar item a ítem
                return this.mensaje() == otroRecordatorio.mensaje() && this.fecha().equals(otroRecordatorio.fecha()) && this.horario().equals(otroRecordatorio.horario());
    }

}
