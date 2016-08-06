package culik.br.com.listacompra.ui;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import culik.br.com.listacompra.FaceApplication;
import culik.br.com.listacompra.R;
import culik.br.com.listacompra.utils.model.Config;

public class Configuracao extends Activity {
    @BindView(R.id.checkBox)
    CheckBox check;
    @BindView(R.id.checkBox2)
    CheckBox check2;
    @BindView(R.id.checkBox3)
    CheckBox check3;
    @BindView(R.id.checkBox4)
    CheckBox check4;
    @BindView(R.id.checkBox5)
    CheckBox check5;
    @BindView(R.id.checkBox6)
    CheckBox check6;
    @BindView(R.id.editText5)
    EditText edit5;
    @BindView(R.id.editText6)
    EditText edit6;
    @BindView(R.id.editText7)
    EditText edit7;
    @BindView(R.id.editText8)
    EditText edit8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        ButterKnife.bind(this);
        Config c = ((FaceApplication)getApplication()).getConfig();
        check.setChecked(c.isUseFaceBook());
        check2.setChecked(c.isSendEmail());
        check3.setChecked(c.isSendWhats());
        check4.setChecked(c.isSendSms());
        check5.setChecked(c.isSendProdutoAuto());
        check6.setChecked(c.isSendMercadoAutop());
        edit5.setText(c.getCabecSms());
        edit6.setText(c.getRodapeSMS());
        edit7.setText(c.getCabecEmail());
        edit8.setText(c.getRodapeEmail());

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setTitle("Configuraçao");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_salva_config, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.salva_config) {

            Config c = new Config(check.isChecked(),check2.isChecked(),check3.isChecked(),check4.isChecked(),
                    edit5.getText().toString(),edit6.getText().toString(),edit7.getText().toString(),edit8.getText().toString(),check5.isChecked(),check6.isChecked());
            ((FaceApplication)getApplication()).setConfig(c);
            return true;
        }
        if (id == R.id.cancela_config) {
            finish();
        return true;
        }
        return super.onOptionsItemSelected(item);
    }
}