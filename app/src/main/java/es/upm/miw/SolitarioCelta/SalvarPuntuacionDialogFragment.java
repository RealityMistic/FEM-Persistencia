package es.upm.miw.SolitarioCelta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class SalvarPuntuacionDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(View.inflate(main, R.layout.salvar_puntuacion, null))
                .setTitle(R.string.txtDialogoFinalTitulo)
                .setPositiveButton(
                        R.string.SiSalvarPuntuacionDialogOption,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String nombreJugador = ((EditText) SalvarPuntuacionDialogFragment.this
                                        .getDialog().findViewById(R.id.usuarioNombre))
                                        .getText().toString();

                                if (nombreJugador.length() > 0) {
                                    main.getMJuego().salvarPuntuacion(main, nombreJugador);
                                    new AlertDialogFragment().show(getFragmentManager(),
                                            "ALERT DIALOG");
                                }
                            }
                        }
                )
                .setNegativeButton(
                        R.string.NoSalvarPuntuacionDialogOption,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SalvarPuntuacionDialogFragment.this.getDialog().cancel();
                            }
                        }
                );

        return builder.create();
    }
}
