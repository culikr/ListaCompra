package culik.br.com.listacompra.ui;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.OnClick;
import culik.br.com.listacompra.R;
import culik.br.com.listacompra.utils.database.ProdutoDataSource;
import culik.br.com.listacompra.utils.model.Produto;

public class Cadastro_Produto extends Activity {

    private ProdutoDataSource pr ;
    private static final int REQUISITAR_CODBAR = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_produto);
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
        EditText te = (EditText) findViewById(R.id.editText);
        EditText te2 = (EditText) findViewById(R.id.editText2);
        EditText te3 = (EditText) findViewById(R.id.editText3);
        Produto c = new Produto(te.getText().toString(),te3.getText().toString(),Double.valueOf(te2.getText().toString()));
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
        startActivityForResult(i,REQUISITAR_CODBAR);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUISITAR_CODBAR) {
            if (resultCode == Activity.RESULT_OK) {
                EditText te = (EditText)findViewById(R.id.editText4);
                te.setText(data.getStringExtra("nome"));

            }

            }
    }

}
