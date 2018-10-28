package es.upm.miw.SolitarioCelta;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;



import java.util.ArrayList;

import static es.upm.miw.SolitarioCelta.PuntuacionesContract.PuntuacionEntry;

public class RepositorioPuntuaciones extends SQLiteOpenHelper {

    private static final String TAG = "MiW";
    // Nombre de la base de datos
    private static final String DB_NAME = "solitario-celta-puntuaciones.db";

    // Número de version
    private static final int DB_VERSION = 1;

    public RepositorioPuntuaciones( @Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, " creando tabla...");
        String consultaSQL = "IF NOT EXIST (SELECT * FROM " + PuntuacionEntry.TABLE_NAME
                + " ) BEGIN " + "CREATE TABLE " + PuntuacionEntry.TABLE_NAME + " ("
                + Integer.toString(PuntuacionEntry.COL_NAME_ID) + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PuntuacionEntry.COL_NAME_NOMBRE + " TEXT, "
                + PuntuacionEntry.COL_NAME_NUM_PIEZAS + " INTEGER,"
                + PuntuacionEntry.COL_NAME_DIA_HORA + " TEXT) END;";
        db.execSQL(consultaSQL);

        /*
        String consulta2 = "SELECT * FROM " + PuntuacionEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(consulta2,null);
        cursor.moveToFirst();
        Log.i(TAG, Integer.toString(cursor.getInt(cursor.getInt(PuntuacionEntry.COL_NAME_ID))));
            Log.i(TAG, cursor.getString(cursor.getColumnIndex(PuntuacionEntry.COL_NAME_NOMBRE)));
            Log.i(TAG, Integer.toString(cursor.getInt(cursor.getColumnIndex(PuntuacionEntry.COL_NAME_NUM_PIEZAS))));
            Log.i(TAG, cursor.getString(cursor.getColumnIndex(PuntuacionEntry.COL_NAME_DIA_HORA)));
            cursor.moveToLast();
        Log.i(TAG, cursor.getString(cursor.getColumnIndex(PuntuacionEntry.COL_NAME_NOMBRE)));
*/
        Log.i(TAG, " objeto creado, tabla creadA?");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String consultaSQL = "DROP TABLE IF EXISTS " + PuntuacionEntry.TABLE_NAME;
        db.execSQL(consultaSQL);
        onCreate(db);
    }

    /**
     * Devuelve el n&uacute;mero de Platos en la tabla
     *
     * @return N&uacute;mero de Platos
     */
    public long count() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, PuntuacionEntry.TABLE_NAME);
    }

    /**
     * Recupera todos las Platos de la tabla
     *
     * @return array de Platos
     */
    public ArrayList<Puntuacion> getAll() {
        String consultaSQL = "SELECT * FROM " + PuntuacionEntry.TABLE_NAME;
        ArrayList<Puntuacion> listaPuntuaciones = new ArrayList<>();

        // Accedo a la DB en modo lectura
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Puntuacion puntuacion = new Puntuacion(
                        cursor.getInt(cursor.getInt(PuntuacionEntry.COL_NAME_ID)),
                        cursor.getString(cursor.getColumnIndex(PuntuacionEntry.COL_NAME_NOMBRE)),
                        cursor.getInt(cursor.getColumnIndex(PuntuacionEntry.COL_NAME_NUM_PIEZAS)),
                        cursor.getString(cursor.getColumnIndex(PuntuacionEntry.COL_NAME_DIA_HORA))
                );

                listaPuntuaciones.add(puntuacion);
                cursor.moveToNext();
            }
        }

        cursor.close();
        db.close();

        return listaPuntuaciones;
    }

    public ArrayList<Puntuacion> getTopTen() {
        String consultaSQL = "SELECT * FROM " + PuntuacionEntry.TABLE_NAME
                + " ORDER BY " + PuntuacionEntry.COL_NAME_NUM_PIEZAS + " DESC LIMIT 10";
        Log.i(TAG, " repo intentar sacar top ten...");
        ArrayList<Puntuacion> listaTopTenPuntuaciones = new ArrayList<>();

        // Accedo a la DB en modo lectura
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);
        Log.i(TAG, " repo intentar sacar top ten - cursor...");
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Puntuacion puntuacion = new Puntuacion(
                        cursor.getInt(cursor.getInt(PuntuacionEntry.COL_NAME_ID)),
                        cursor.getString(cursor.getColumnIndex(PuntuacionEntry.COL_NAME_NOMBRE)),
                        cursor.getInt(cursor.getColumnIndex(PuntuacionEntry.COL_NAME_NUM_PIEZAS)),
                        cursor.getString(cursor.getColumnIndex(PuntuacionEntry.COL_NAME_DIA_HORA))
                );

                listaTopTenPuntuaciones.add(puntuacion);
                cursor.moveToNext();
            }
        }
        Log.i(TAG, " repo intentar después de recorrer...");

        cursor.close();
        db.close();
        Log.i(TAG, " después de leer puntuaciones");
        return listaTopTenPuntuaciones;
    }

    public void guardarPuntuacion(Puntuacion nuevaPuntuacion){

        String insercionSql = "INSERT INTO " + PuntuacionesContract.PuntuacionEntry.TABLE_NAME + " VALUES ( "
                + Integer.toString(nuevaPuntuacion.getId()) + ", "
                + nuevaPuntuacion.getNombreJugador() + ", "
                + nuevaPuntuacion.getNumPiezas() + ", "
                + nuevaPuntuacion.getMomento() + " );";
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i(TAG, " antes de guardar puntuacion");
        db.execSQL(insercionSql);
        Log.i(TAG, " después de guardar puntuacion");
    }
}
