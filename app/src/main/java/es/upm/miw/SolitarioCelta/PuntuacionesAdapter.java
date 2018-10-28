package es.upm.miw.SolitarioCelta;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class PuntuacionesAdapter extends ArrayAdapter {
    private Context context;
    private int puntuacionStructureID;
    private ArrayList<Puntuacion> puntuacionesList;

    public PuntuacionesAdapter(Context context, int PuntuacionStructureID, List<Puntuacion> puntuacionesList) {
        super(context, PuntuacionStructureID, puntuacionesList);
        this.setContext(context);
        this.setPuntuacionStructureID(PuntuacionStructureID);
        this.setPuntuacionesList((ArrayList<Puntuacion>) puntuacionesList);
    }

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    private void setContext(Context context) {
        this.context = context;
    }

    private int getPuntuacionStructureID() {
        return puntuacionStructureID;
    }

    private void setPuntuacionStructureID(int PuntuacionStructureID) {
        this.puntuacionStructureID = PuntuacionStructureID;
    }

    private ArrayList<Puntuacion> getPuntuacionesList() {
        return puntuacionesList;
    }

    private void setPuntuacionesList(ArrayList<Puntuacion> puntuacionesList) {
        this.puntuacionesList = puntuacionesList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout view;

        if (null != convertView)
            view = (LinearLayout) convertView;
        else {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            view = (LinearLayout) layoutInflater.inflate(this.getPuntuacionStructureID(), parent, false);
        }

        TextView nombreJugadorTextView = (TextView) view.findViewById(R.id.nombreJugador);
        nombreJugadorTextView.setText(this.getPuntuacionesList().get(position).getNombreJugador());

        TextView numPiezasTextView = (TextView) view.findViewById(R.id.numPiezas);
        numPiezasTextView.setText(String.format(Locale.getDefault(), "%d",
                this.getPuntuacionesList().get(position).getNumPiezas()));

        TextView PuntuacionDateTextView = (TextView) view.findViewById(R.id.momento);
        PuntuacionDateTextView.setText(this.getPuntuacionesList().get(position).getMomento());

        return view;
    }
}