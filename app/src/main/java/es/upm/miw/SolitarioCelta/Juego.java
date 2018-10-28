package es.upm.miw.SolitarioCelta;

import java.io.Serializable;

public class Juego implements Serializable {
        private String tablero;
        private String nombre;

        public Juego(String tablero, String nombre) {
            this.setTablero(tablero);
            this.setNombre(nombre);
        }

        public String getTablero() {
            return tablero;
        }

        private void setTablero(String tablero) {
            this.tablero = tablero;
        }

        public String getNombre() {
            return nombre;
        }

        private void setNombre(String nombre) {
            this.nombre = nombre;
        }

}


