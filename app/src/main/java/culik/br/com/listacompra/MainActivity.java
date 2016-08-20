package culik.br.com.listacompra;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import culik.br.com.listacompra.fragment.ListaCompraDetalheFragment;
import culik.br.com.listacompra.fragment.ListaCompraFragment;
import culik.br.com.listacompra.utils.model.ListaCompra;
import culik.br.com.listacompra.utils.model.ListaProduto;
import culik.br.com.listacompra.utils.utils.BaseActivity;

/**
 * Created by LUIZ on 11/05/2016.
 */
public class MainActivity extends BaseActivity implements ListaCompraFragment.OnFragmentInteractionListener {

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListaCompraFragment e = new ListaCompraFragment();
        // ArrayList<Produto> pr;
        // ArrayList<ListaCompra> lc ;

        //ProdutoDataSource p = new ProdutoDataSource(this);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load
        // titles
        // from
        // strings.xml

        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);// load icons from
        set(navMenuTitles, navMenuIcons);
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

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.framelayout_left, listaCompraFragment);

        if (findViewById(R.id.framelayout_right) != null) {
            boolean mTwoPane = true;
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
        FragmentManager fragmentManager = getFragmentManager();

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

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Override this method in the activity that hosts the Fragment and call super
        // in order to receive the result inside onActivityResult from the fragment.
        super.onActivityResult(requestCode, resultCode, data);
    }
}
