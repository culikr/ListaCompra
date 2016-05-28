package culik.br.com.listacompra.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import culik.br.com.listacompra.R;
import culik.br.com.listacompra.utils.database.ListaCompraDataSource;
import culik.br.com.listacompra.utils.database.ProdutoDataSource;
import culik.br.com.listacompra.utils.model.ListaCompra;

public class Cadastra_Lista extends AppCompatActivity {
    private ListaCompraDataSource pr ;
    private ListaCompra listaCompraSelecionado;
    private EditText edNome,edTelefone,edEmail,edMensagem;
    private int posicao ;
    private int lEdicao = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_altera_lista_compra);
        pr = new ListaCompraDataSource(this);
        pr.open();
        edNome = (EditText) findViewById(R.id.edNome);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edTelefone = (EditText) findViewById(R.id.edTelefone);
        edMensagem = (EditText) findViewById(R.id.edMensagem);
        listaCompraSelecionado = (ListaCompra) this.getIntent().getSerializableExtra("lista");
        posicao = this.getIntent().getIntExtra("posicao",-1);
        lEdicao = this.getIntent().getIntExtra("ledicao",-1);
        if (listaCompraSelecionado != null)
        {
            edNome.setText(listaCompraSelecionado.getsNome());
            edEmail.setText(listaCompraSelecionado.getsEmail());
            edTelefone.setText(listaCompraSelecionado.getsTelefone());
            edMensagem.setText(listaCompraSelecionado.getsMensagem());
            edNome.setEnabled(false);

        }


    }

    public void editarListaCompra( View v){
        ListaCompra lista;
        String Nome = edNome.getText().toString();
        String Telefone = edTelefone.getText().toString();
        String Email=edEmail.getText().toString();
        String mensagem = edMensagem.getText().toString();
        lista = new ListaCompra(Nome,"",Email,Telefone,mensagem);
        if (lEdicao == 1)
            pr.atualizaListaCompra(lista);
        else
           pr.insereCompra(lista);
        Intent i = new Intent();
        i.putExtra("lista",lista);
        i.putExtra("posicao",posicao);
        setResult(RESULT_OK,i);
        finish();

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


}
