package culik.br.com.listacompra;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import culik.br.com.listacompra.fragment.ListaCompraDetalheFragment;
import culik.br.com.listacompra.fragment.ListaCompraFragment;
import culik.br.com.listacompra.utils.model.ListaCompra;
import culik.br.com.listacompra.utils.model.ListaProduto;

/**
 * Created by LUIZ on 11/05/2016.
 */
public class MainActivity extends AppCompatActivity implements ListaCompraFragment.OnFragmentInteractionListener {


    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListaCompraFragment e = new ListaCompraFragment();
       // ArrayList<Produto> pr;
        // ArrayList<ListaCompra> lc ;
        super.onCreate(savedInstanceState);
        //ProdutoDataSource p = new ProdutoDataSource(this);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        /*
        p.open();
        pr = p.getAllProduto();
        if ( pr.size() ==0 ) {
            p.insereProduto(new Produto("Feijao", "Zafari", 3.4));
            p.insereProduto(new Produto("Arroz", "Zafari", 2.78));
            p.insereProduto(new Produto("Batata", "Zafari", 2.41));
            p.insereProduto(new Produto("Cebola", "Zafari", 3.5));
            p.insereProduto(new Produto("Banana", "Zafari", 1.57));
        }
        p.close();
        ListaCompraDataSource c = new ListaCompraDataSource(this);
        c.open();
        lc = c.getAllProduto();
            if ( lc.size() == 0 ){
            c.insereCompra(new ListaCompra("Casa", "", "", "",""));
            c.insereCompra(new ListaCompra("Casa3", "", "", "",""));
            c.insereCompra(new ListaCompra("Casa2", "", "", "",""));
            c.insereCompra(new ListaCompra("frutas", "", "", "",""));

        }
        c.close();
       */

        if (savedInstanceState != null)
            return;
        ListaCompraFragment listaCompraFragment = new ListaCompraFragment();

        FragmentManager fragmentManager =  getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.framelayout_left, listaCompraFragment);

        if (findViewById(R.id.framelayout_right) != null) {
            ListaProduto pro = new ListaProduto();
       

            Bundle bundle = new Bundle();
            bundle.putSerializable("compra", pro);

            ListaCompraDetalheFragment listaCompraDetailFragment = new ListaCompraDetalheFragment();
            listaCompraDetailFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.framelayout_right, listaCompraDetailFragment);
        }

        fragmentTransaction.commit();

    }

    @Override
    public void onListaSelected(ListaCompra prod) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        int containerViewId = R.id.framelayout_left;

        if (findViewById(R.id.framelayout_right) != null)
            containerViewId = R.id.framelayout_right;

        Bundle bundle = new Bundle();
        bundle.putSerializable("compra", prod);

        ListaCompraDetalheFragment listaCompraDetailFragment = new ListaCompraDetalheFragment();
        listaCompraDetailFragment.setArguments(bundle);
        fragmentTransaction.replace(containerViewId, listaCompraDetailFragment);

        //if (findViewById(R.id.framelayout_right) == null)
        fragmentTransaction.addToBackStack(null);


        fragmentTransaction.commit();
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_lista, menu);
//        return true;
//    }

    //    @Override
//    public boolean onContextItemSelected(MenuItem item) {

    //      switch (item.getItemId()) {
//            case R.id.produto: {
//                Intent i = new Intent(this, culik.br.com.listacompra.ui.ListaProduto.class);

//                startActivity(i);
//                break;
//            }
//        }
//        return true;
//    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
