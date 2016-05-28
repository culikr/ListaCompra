package culik.br.com.listacompra.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import culik.br.com.listacompra.R;
import culik.br.com.listacompra.utils.adapter.ListaCompraProdutoAdapter;
import culik.br.com.listacompra.utils.adapter.ListaProdutoAdapter;
import culik.br.com.listacompra.utils.database.ProdutoDataSource;
import culik.br.com.listacompra.utils.model.Produto;

public class ListaProduto extends AppCompatActivity {
    private ProdutoDataSource pd;
    private ListView list;
    private ListaProdutoAdapter adap;
    private ArrayList<Produto> lista;
    private final static int TELA_PRODUTO=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produto);
        list = (ListView)findViewById(R.id.listView2);
        pd = new ProdutoDataSource( this );
        pd.open();
        lista=pd.getAllProduto();
        adap = new ListaProdutoAdapter(this,lista);
        list.setAdapter(adap);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_salva_produto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.novo: {
                Intent i = new Intent(this,Cadastro_Produto.class);
                startActivityForResult(i,TELA_PRODUTO);
                lista=pd.getAllProduto();
                adap.updateResults(lista);
                break;
            }
            case R.id.cancela:{
                finish();
                break;
            }

        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TELA_PRODUTO)
            if (resultCode == Activity.RESULT_OK) {
                adap.updateResults(pd.getAllProduto());
                atualizaListaCompra();

                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cadastro cancelado!", Toast.LENGTH_SHORT).show();
            }
    }
    private void atualizaListaCompra() {
        pd.open();

        ((ListaProdutoAdapter) list.getAdapter()).notifyDataSetChanged();
        pd.close();
    }


}
