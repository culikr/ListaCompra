package culik.br.com.listacompra.utils.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import culik.br.com.listacompra.R;

/**
 * Created by LUIZ on 10/07/2016.
 */
public class DialogoAlerta {
    /**
     * Função para mostrar um simples dialog de alerta
     * @param context - contexto da aplicação
     * @param title - titulo do dialog de alerta
     * @param message - mensagem de alerta
     * @param status - sucesso/falha
     *               - null se nao quiser usar icone
     * */
    public void showAlertDialog(Context context, String title, String message,
                                Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Seta o titulo do dialogo
        alertDialog.setTitle(title);

        // Seta a mensagem do dialogo
        alertDialog.setMessage(message);

        if(status != null)
            // Seta o icone do dialogo
            alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        // Seta o botao ok
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Mostra a mensagem de alerta
        alertDialog.show();
    }
}
