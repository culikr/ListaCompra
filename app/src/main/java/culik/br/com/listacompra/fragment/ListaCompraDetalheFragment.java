package culik.br.com.listacompra.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import culik.br.com.listacompra.R;
import culik.br.com.listacompra.ui.Lista_compra_adiciona_produto;
import culik.br.com.listacompra.utils.adapter.ListaCompraProdutoAdapter;
import culik.br.com.listacompra.utils.database.ListaProdutoDataSource;
import culik.br.com.listacompra.utils.model.ListaCompra;
import culik.br.com.listacompra.utils.model.ListaProduto;

/**
 * Created by LUIZ on 10/05/2016.
 */
public class ListaCompraDetalheFragment extends Fragment {

    private ListaProdutoDataSource ld;
    private ArrayList<ListaProduto> lc;
    private ListaCompraProdutoAdapter ef;
    private ListView lv;
    private ListaCompra listaCompra ;
    private static final int TELA_PRODUTO=4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_compra_detalhe,null);
        lv =  (ListView)view.findViewById(R.id.listView);
        ld = new ListaProdutoDataSource(getActivity());
        ld.open();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle.containsKey("compra")) {
            listaCompra =(ListaCompra) bundle.getSerializable("compra");
            if (listaCompra != null) {
                lc = ld.getAllListaProduto( listaCompra.getIdLista());
            }
            ef = new ListaCompraProdutoAdapter(getActivity(), lc);
            lv.setAdapter(ef);
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_lista_adiciona_produto, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()) {
            case R.id.novo_produto_lista: {
                i = new Intent(getActivity(), Lista_compra_adiciona_produto.class);
                i.putExtra("compra",listaCompra);
                startActivityForResult(i,TELA_PRODUTO);

                break;
            }
            default:
                return false;
        }
        return true;
    }

@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TELA_PRODUTO)
            if (resultCode == Activity.RESULT_OK) {
            ef.updateResults(ld.getAllListaProduto(listaCompra.getIdLista()));
            atualizaListaCompra();

            Toast.makeText(getActivity(), "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Cadastro cancelado!", Toast.LENGTH_SHORT).show();
        }
    }
    private void atualizaListaCompra() {
        ld.open();

        ((ListaCompraProdutoAdapter) lv.getAdapter()).notifyDataSetChanged();
        ld.close();
    }

}
