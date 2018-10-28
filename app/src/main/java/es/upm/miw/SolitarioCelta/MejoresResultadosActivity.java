package es.upm.miw.SolitarioCelta;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MejoresResultadosActivity extends Activity {
    private static final String TAG = "MiW";
    RepositorioPuntuaciones repositorioPuntuaciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_mejores);
            repositorioPuntuaciones =new RepositorioPuntuaciones(this);
            ListView puntuacionesListView = (ListView) findViewById(R.id.mejoresList);
            Log.i(TAG, " intentando recuperar top ten...");
            ArrayList<Puntuacion> puntuaciones = repositorioPuntuaciones.getTopTen();
        Log.i(TAG, " top ten recuperado");
            PuntuacionesAdapter adapter = new PuntuacionesAdapter(this, R.layout.puntuacion, puntuaciones);

            puntuacionesListView.setAdapter(adapter);
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            getMenuInflater().inflate(R.menu.opciones_mejores, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.borrarMejores:
                    Ficheros<Puntuacion> ficheros = new Ficheros<>(Ficheros.PUNTUACIONES);
                    ListView puntuacionesListView = (ListView) findViewById(R.id.puntuacionesList);

                    ficheros.deleteFileInfo(this, Context.MODE_PRIVATE);
                    puntuacionesListView.setAdapter(null);
                    Toast.makeText(this, getString(R.string.toastBorrarPuntuaciones), Toast.LENGTH_SHORT
                    ).show();
                    return true;
                default:
                    this.finish();
                    break;
            }

            return true;
        }
}

