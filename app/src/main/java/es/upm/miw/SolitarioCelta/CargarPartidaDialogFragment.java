package es.upm.miw.SolitarioCelta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class CargarPartidaDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();
        final CharSequence[] nombresJuegos = main.getMJuego().recuperarJuegos(main);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.CargarPartidaDialogTitle))
                .setItems(nombresJuegos,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                main.getMJuego().cargarJuegoEnTablero(main, nombresJuegos[which].toString());
                            }
                        }
                );

        return builder.create();
    }
}
