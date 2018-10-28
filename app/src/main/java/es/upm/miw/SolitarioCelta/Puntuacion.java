package es.upm.miw.SolitarioCelta;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Puntuacion implements Serializable {

    private int id;
    private String nombreJugador;
    private int numPiezas;
    private String momento;

    public Puntuacion(int id, String nombreJugador, int numPiezas, String momento){
        this.id = id;
        this.nombreJugador = nombreJugador;
        this.numPiezas = numPiezas;
        this.momento = momento;
    }
    public void incrementar(){
        numPiezas++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getNumPiezas() {
        return numPiezas;
    }

    public void setNumPiezas(int numPiezas) {
        this.numPiezas = numPiezas;
    }

    public String getMomento() {
        return momento;
    }

    public void setMomento(String momento) {
        this.momento = momento;
    }

    protected Puntuacion(Parcel in) {
        id = in.readInt();
        nombreJugador = in.readString();
        numPiezas = in.readInt();
        momento = in.readString();
    }


}
