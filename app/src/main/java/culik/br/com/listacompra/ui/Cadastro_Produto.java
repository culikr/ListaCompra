package culik.br.com.listacompra.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.OnClick;
import culik.br.com.listacompra.R;
import culik.br.com.listacompra.utils.database.MercadoDataSource;
import culik.br.com.listacompra.utils.database.ProdutoDataSource;
import culik.br.com.listacompra.utils.model.Mercados;
import culik.br.com.listacompra.utils.model.Produto;

public class Cadastro_Produto extends Activity {

    private ProdutoDataSource pr ;
    private static final int REQUISITAR_CODBAR = 100;
    private EditText te;
    private EditText te2;

    private EditText te4;
    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_produto);
        te = (EditText) findViewById(R.id.editText);
        te2 = (EditText) findViewById(R.id.editText2);
        //EditText te3 = (EditText) findViewById(R.id.editText3);
        te4 = (EditText) findViewById(R.id.editText4);
        sp = (Spinner) findViewById(R.id.spinner);
        MercadoDataSource mc = new MercadoDataSource(this);
        mc.open();

        ArrayList<Mercados> lc = mc.getAllMercados();
        mc.close();
        ArrayAdapter<Mercados> locationAdapter = new ArrayAdapter<>(this, R.layout.spinner, lc);
        sp.setAdapter(locationAdapter);

        sp.setFocusable(true);
        sp.setFocusableInTouchMode(true);
        
        pr = new ProdutoDataSource(this);
        pr.open();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_salva_produto,menu);
        return true;

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cancela: {
                finish();
                break;
            }
        }
        return true;
    }

    public void editarProduto( View v )
    {
        salvaDados();
    }
    private void salvaDados(){

        Mercados mer = (Mercados) sp.getSelectedItem();


        Produto c = new Produto(te.getText().toString(), mer.getId(), Double.valueOf(te2.getText().toString()), te4.getText().toString());
        pr.insereProduto(c);
        Intent i = new Intent();
        i.putExtra("lista",c);
        setResult(RESULT_OK,i);
        finish();
    }


    @Override
    public void onPause() {
        pr.close();
        super.onPause();
    }
    @Override
    public void onDestroy() {
        pr.close();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        cancelarEdicao();
        super.onBackPressed();
    }

    public void cancelarEdicao(View v){
        cancelarEdicao();
    }

    private void cancelarEdicao(){
        Toast.makeText(this,"Edicao cancelada!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnClick(R.id.button)
    public void buscaCodBar( View v ){
        Intent i = new Intent(this,LeituraCodBarras.class);


        try {

            startActivityForResult(i, REQUISITAR_CODBAR);
        } catch (Exception e) {
            Log.d("codbar", e.toString());
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == REQUISITAR_CODBAR) {
                if (resultCode == Activity.RESULT_OK) {
                    //                  EditText te = (EditText) findViewById(R.id.editText4);
                    te4.setText(data.getStringExtra("nome"));
                    Log.e("Codbar",data.getStringExtra("nome"));


                }

            }
        } catch (Exception F) {
            Log.d("codbar", F.toString());
        }
    }

}


