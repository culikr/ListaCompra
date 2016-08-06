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
     * */
    public void showAlertDialog(Context context) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Seta o titulo do dialogo
        alertDialog.setTitle("Erro de conexao na internet");

        // Seta a mensagem do dialogo
        alertDialog.setMessage("Conecte a internet para usar");

        if((Boolean) false != null)
            // Seta o icone do dialogo
            alertDialog.setIcon(false ? R.drawable.success : R.drawable.fail);

        // Seta o botao ok
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Mostra a mensagem de alerta
        alertDialog.show();
    }
}
