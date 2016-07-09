package culik.br.com.listacompra.ui;

/**
 * Created by LUIZ on 09/07/2016.
 */

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import culik.br.com.listacompra.R;


public class ActivityAbout extends AppCompatActivity {

    private TextView lblVersao;
    private Button btContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setTitle(getString(R.string.title_activity_about));

        lblVersao = (TextView) findViewById(R.id.lbl_versao);
        btContato = (Button) findViewById(R.id.bt_contato);

        try {
            lblVersao.setText(getString(R.string.txt_versao) + " " + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        btContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inEmail = new Intent(Intent.ACTION_SEND);
                inEmail.setType("plain/text");
                inEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.txt_email)});
                inEmail.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.txt_email_assunto));
                startActivity(Intent.createChooser(inEmail, "Enviar e-mail"));
            }
        });





}

}
