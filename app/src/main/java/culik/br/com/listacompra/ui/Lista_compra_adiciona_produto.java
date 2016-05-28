package culik.br.com.listacompra.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import culik.br.com.listacompra.R;
import culik.br.com.listacompra.utils.database.ListaProdutoDataSource;
import culik.br.com.listacompra.utils.database.ProdutoDataSource;
import culik.br.com.listacompra.utils.model.*;
import culik.br.com.listacompra.utils.model.ListaProduto;

public class Lista_compra_adiciona_produto extends AppCompatActivity {

    private ProdutoDataSource ld;
    private ListaProdutoDataSource lp;
    private Spinner sp;
    private EditText edQuant;
    private ArrayList<Produto> lc;
    private ListaCompra listaCompra ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle b = getIntent().getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compra_adiciona_produto);

        if(b.containsKey("compra"))
            listaCompra =(ListaCompra) b.getSerializable("compra");


        sp = (Spinner) findViewById(R.id.spinner);
        edQuant = (EditText) findViewById(R.id.editText4);
        ld = new ProdutoDataSource(this);
        lp = new ListaProdutoDataSource(this);
        ld.open();
        lp.open();
        lc = ld.getAllProduto();
        ld.close();
        ArrayAdapter locationAdapter = new ArrayAdapter(this, R.layout.spinner, lc);
        sp.setAdapter(locationAdapter);

        sp.setFocusable(true);
        sp.setFocusableInTouchMode(true);

    }

    public void SalvaProdutoCompra(View v){

        Produto c = (Produto) sp.getSelectedItem();
        String quant = edQuant.getText().toString();
        ListaProduto lista = new ListaProduto(c.getIdProduto(),listaCompra.getIdLista(),Double.valueOf(quant),"");
        Intent i = new Intent();
        i.putExtra("lista",lista);
        if ( v.getId() == R.id.lista_produto_salva_continua)
        {
            lp.insereLista(lista);
            edQuant.setText("");
            sp.requestFocus();
            sp.performClick();

            setResult(RESULT_OK,i);

        }
        else if (v.getId() == R.id.lista_produto_salvasai){
            lp.insereLista(lista);
            setResult(RESULT_OK,i);
            finish();
        }

    }
    @Override
    public void onBackPressed() {
        cancelarEdicao();
        super.onBackPressed();
        lp.close();
    }

    public void cancelarEdicaoLista(View v){
        cancelarEdicao();
    }

    private void cancelarEdicao(){
        Toast.makeText(this,"Edicao cancelada!", Toast.LENGTH_SHORT).show();
        finish();
    }


}
