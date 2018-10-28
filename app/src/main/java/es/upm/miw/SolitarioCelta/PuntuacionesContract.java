package es.upm.miw.SolitarioCelta;

import android.provider.BaseColumns;

public class PuntuacionesContract {


        private PuntuacionesContract() {};

        /* Inner class that defines the table contents */
        public static class PuntuacionEntry implements BaseColumns {
            public static final String TABLE_NAME = "puntuaciones";
            public static final int COL_NAME_ID          = 1;
            public static final String COL_NAME_NOMBRE      = "Nombre del Jugador";
            public static final String COL_NAME_NUM_PIEZAS = "Número de Piezas";
            public static final String COL_NAME_DIA_HORA     = "Momento";
        }
    }