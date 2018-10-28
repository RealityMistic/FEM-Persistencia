package es.upm.miw.SolitarioCelta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SalvarPartidaDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(View.inflate(main, R.layout.salvar_partida, null))
                .setTitle(R.string.guardarPartidaDialogTitle)
                .setPositiveButton(
                        R.string.SiGuardarDialogOption,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String gameName = ((EditText) SalvarPartidaDialogFragment.this
                                        .getDialog().findViewById(R.id.nombreJuego))
                                        .getText().toString();

                                if (gameName.length() > 0)
                                    main.getMJuego().guardarPartida(main, gameName);
                            }
                        }
                )
                .setNegativeButton(
                        R.string.NoGuardarDialogOption,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SalvarPartidaDialogFragment.this.getDialog().cancel();
                            }
                        }
                );

        return builder.create();
    }
}
