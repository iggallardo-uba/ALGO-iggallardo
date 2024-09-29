package aed;

public class Horario {
    private int hora;
    private int minutos;

    public Horario(int hora, int minutos) {
        this.hora = hora;
        this.minutos = minutos;
    }

    public int hora() {
        return hora;
    }

    public int minutos() {
        return minutos;
    }

    @Override public String toString() {
        if(this.hora() < 10 && this.minutos() < 10) return "0" + this.hora()+":0"+this.minutos();
        
        if(this.hora() < 10) return "0"+this.hora()+":"+this.minutos();
        
        if(this.minutos() < 10) return this.hora()+":0"+this.minutos();

        return this.hora()+":"+this.minutos();
        
    }

    @Override public boolean equals(Object otro) {
        return (this.toString() == otro.toString());
    }

}
