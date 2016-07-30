 package culik.br.com.listacompra.fragment;

 import android.app.Activity;
 import android.content.Intent;
 import android.os.Bundle;
 import android.support.v4.app.Fragment;
 import android.telephony.SmsManager;
 import android.view.ContextMenu;
 import android.view.LayoutInflater;
 import android.view.Menu;
 import android.view.MenuInflater;
 import android.view.MenuItem;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.AdapterView;
 import android.widget.ListView;
 import android.widget.TextView;
 import android.widget.Toast;

 import java.util.ArrayList;

 import culik.br.com.listacompra.R;
 import culik.br.com.listacompra.ui.ActivityAbout;
 import culik.br.com.listacompra.ui.Cadastra_Lista;
 import culik.br.com.listacompra.ui.Configuracao;
 import culik.br.com.listacompra.ui.ListaProduto;
 import culik.br.com.listacompra.utils.adapter.ListaCompraAdapter;
 import culik.br.com.listacompra.utils.database.ListaCompraDataSource;
 import culik.br.com.listacompra.utils.database.ListaProdutoDataSource;
 import culik.br.com.listacompra.utils.model.ListaCompra;

/**
 *  ListaCompraFragment
 *  Cria um fragmento de lista compra
 */
public class ListaCompraFragment extends Fragment {
    OnFragmentInteractionListener listener;
    private ListaCompraDataSource ld;
    private ArrayList<ListaCompra> lc;
    private ListaCompraAdapter ef;
    private ListView listaCompra;
    private static final int CADASTRAR_LISTA = 1;
    private static final int EDITAR_LISTA = 2;
    private int posicaoSelecionada = -1;


    private ListaCompra listaCompraSelecionado;

    public ListaCompraFragment() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_lista_compra_list, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listaCompra = (ListView) view.findViewById(R.id.lista_produto);
        TextView te = (TextView) view.findViewById(R.id.TextView01);

        ld = new ListaCompraDataSource(getActivity().getBaseContext());
        ld.open();
        lc = ld.getAllProduto();
        ef = new ListaCompraAdapter(getActivity().getBaseContext(), lc);
        listaCompra.setAdapter(ef);
        registerForContextMenu(listaCompra);
        listaCompra.setOnItemClickListener(onListaCompraListViewItemClickListener);

        listaCompra.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                listaCompraSelecionado = (ListaCompra) adapterView.getItemAtPosition(pos);
                posicaoSelecionada = pos;
                return false;
            }
        });

    }

    AdapterView.OnItemClickListener onListaCompraListViewItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ListaCompra lista = (ListaCompra) adapterView.getItemAtPosition(i);
            if (listener != null)
                listener.onListaSelected(lista);
        }
    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) activity;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.lista_produto) {
            getActivity().getMenuInflater().inflate(R.menu.menu_lista_compra, menu);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_lista, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i ;
        switch (item.getItemId()) {
            case R.id.produto:
                 i = new Intent(getActivity(), ListaProduto.class);

                getActivity().startActivity(i);
                return true;

            case R.id.adiciona:
                 i = new Intent(getActivity(), Cadastra_Lista.class);

                i.putExtra("posicao", 0);
                i.putExtra("ledicao",0);

                getActivity().startActivityForResult(i, CADASTRAR_LISTA);
                return true;
            case R.id.about:
                i = new Intent(getActivity(), ActivityAbout.class);
                getActivity().startActivity(i);
                return true;
            case R.id.settings:
                i = new Intent(getActivity(), Configuracao.class);
                getActivity().startActivity(i);
                return true;


        }
        return false;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.produto: {
                Intent i = new Intent(getActivity(), ListaProduto.class);

                getActivity().startActivity(i);
                break;
            }
            case R.id.menuAlterar: {
                Intent i = new Intent(getActivity(), Cadastra_Lista.class);
                i.putExtra("lista", listaCompraSelecionado);
                i.putExtra("posicao", posicaoSelecionada);
                i.putExtra("ledicao",1);

                startActivityForResult(i, EDITAR_LISTA);


                break;
            }
            case R.id.menuEnviarSMS: {
                EnviaSms();
                break;
            }
            case R.id.menuEnviarEmail: {
                EnviarEmail();

                break;

            }
            case R.id.menuEnviarWhatsApp:
            {
                EnviaWhatsapp();
            }

            default:
                return false;
        }
        return true;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onPause() {
        ld.close();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        ld.close();
        super.onDestroy();
    }

    public interface OnFragmentInteractionListener {
        void onListaSelected(ListaCompra lista);
    }

    private void EnviaSms() {


        try {
            StringBuilder builder = new StringBuilder(listaCompraSelecionado.getsMensagem());

            ListaProdutoDataSource p = new ListaProdutoDataSource(getActivity());
            p.open();
            ArrayList<culik.br.com.listacompra.utils.model.ListaProduto> c = p.getAllListaProduto(listaCompraSelecionado.getIdLista()  );
            p.close();
            builder.append("\r\n");
            if (c.size()>0)
            {
                culik.br.com.listacompra.utils.model.ListaProduto c1;
                for ( int i =0; i<c.size();i++){
                    c1=c.get(i);
                    builder.append(c1.getsNome()).append("\r\n");
                }
            }

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(listaCompraSelecionado.getsTelefone(), null, builder.toString(), null, null);
            Toast.makeText(getActivity(), "Mensagem enviada", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Envio do SMS falhou, tente novamente.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void EnviarEmail() {
        StringBuilder builder = new StringBuilder(listaCompraSelecionado.getsMensagem());

        ListaProdutoDataSource p = new ListaProdutoDataSource(getActivity());
        p.open();
        ArrayList<culik.br.com.listacompra.utils.model.ListaProduto> c = p.getAllListaProduto(listaCompraSelecionado.getIdLista() );
        p.close();
        builder.append("\r\n");
        if (c.size()>0)
        {
            culik.br.com.listacompra.utils.model.ListaProduto c1;
            for ( int i =0; i<c.size();i++){
                c1=c.get(i);
                builder.append(c1.getsNome()+"\r\n");
            }
        }

        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");


        String aEmailList[] = {listaCompraSelecionado.getsEmail()};

        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);

        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Lista de Compra");

        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, builder.toString() );

        startActivity(emailIntent);

    }


    private void atualizaListaCompra() {
        ld.open();

        ((ListaCompraAdapter) listaCompra.getAdapter()).notifyDataSetChanged();
        ld.close();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CADASTRAR_LISTA) {
            if (resultCode == Activity.RESULT_OK) {
                ListaCompra contato = (ListaCompra) data.getSerializableExtra("lista");
                lc.add(contato);
                atualizaListaCompra();
                Toast.makeText(getActivity(), "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Cadastro cancelado!", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == EDITAR_LISTA) {
                if (resultCode == Activity.RESULT_OK) {
                    ListaCompra listas = (ListaCompra) data.getSerializableExtra("lista");
                    int posicao = data.getIntExtra("posicao", -1);
                    lc.set(posicao, listas);
                    atualizaListaCompra();
                    Toast.makeText(getActivity(), "Alteracao realizado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Alteracao cancelado!", Toast.LENGTH_SHORT).show();
                }

        }


    }

    private void EnviaWhatsapp() {


        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(listaCompraSelecionado.getsTelefone(), null, "ola", null, null);
            Toast.makeText(getActivity(), "Mensagem enviada", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Envio do whatsappjus falhou, tente novamente.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}
