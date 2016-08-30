package culik.br.com.listacompra.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import culik.br.com.listacompra.R;
import culik.br.com.listacompra.utils.database.ListaCompraDataSource;
import culik.br.com.listacompra.utils.database.MercadoDataSource;
import culik.br.com.listacompra.utils.model.ListaCompra;
import culik.br.com.listacompra.utils.model.Mercados;

public class Cadastra_Lista extends Activity {
    private ListaCompraDataSource pr;
    @BindView(R.id.edNome)
    EditText edNome;
    @BindView(R.id.edTelefone)
    EditText edTelefone;
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edMensagem)
    EditText edMensagem;
    @BindView(R.id.spinner)
    Spinner sp;
    private int posicao;
    private int lEdicao = 0;
    private ArrayList<Mercados> lc;
    private MercadoDataSource mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_altera_lista_compra);
        ButterKnife.bind(this);
        sp = (Spinner) findViewById(R.id.spinner);
        mc = new MercadoDataSource(this);
        mc.open();

        lc = mc.getAllMercados();
        mc.close();
        ArrayAdapter locationAdapter = new ArrayAdapter(this, R.layout.spinner, lc);
        sp.setAdapter(locationAdapter);

        sp.setFocusable(true);
        sp.setFocusableInTouchMode(true);
        pr = new ListaCompraDataSource(this);
        pr.open();

        ListaCompra listaCompraSelecionado = (ListaCompra) this.getIntent().getSerializableExtra("lista");
        posicao = this.getIntent().getIntExtra("posicao", -1);
        lEdicao = this.getIntent().getIntExtra("ledicao", -1);
        if (listaCompraSelecionado != null) {
            edNome.setText(listaCompraSelecionado.getsNome());
            edEmail.setText(listaCompraSelecionado.getsEmail());
            edTelefone.setText(listaCompraSelecionado.getsTelefone());
            edMensagem.setText(listaCompraSelecionado.getsMensagem());
            edNome.setEnabled(false);

        }


    }

    public void editarListaCompra(View v) {
        ListaCompra lista;
        String Nome = edNome.getText().toString();
        String Telefone = edTelefone.getText().toString();
        String Email = edEmail.getText().toString();
        String mensagem = edMensagem.getText().toString();
        Mercados mer = (Mercados) sp.getSelectedItem();
        lista = new ListaCompra(Nome, "", Email, Telefone, mensagem, mer.getId());
        if (lEdicao == 1)
            pr.atualizaListaCompra(lista);
        else
            pr.insereCompra(lista);
        Intent i = new Intent();
        i.putExtra("lista", lista);
        i.putExtra("posicao", posicao);
        setResult(RESULT_OK, i);
        finish();

    }

    @Override
    public void onBackPressed() {
        cancelarEdicao();
        super.onBackPressed();
    }

    public void cancelarEdicao(View v) {
        cancelarEdicao();
    }

    private void cancelarEdicao() {
        Toast.makeText(this, "Edicao cancelada!", Toast.LENGTH_SHORT).show();
        finish();
    }


}
