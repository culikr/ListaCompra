package culik.br.com.listacompra.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;

import culik.br.com.listacompra.Login;
import culik.br.com.listacompra.R;
import culik.br.com.listacompra.utils.database.ProdutoDataSource;
import culik.br.com.listacompra.utils.model.ListaProdutos;
import culik.br.com.listacompra.utils.model.Produto;
import culik.br.com.listacompra.utils.model.Produtos;
import culik.br.com.listacompra.utils.service.RetrofitService;

public class SplashScreen extends Activity {


    private static int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new  CarregaDadosAsync().execute();

    }

    private class CarregaDadosAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<Produto> pr;
            RetrofitService serv = new RetrofitService();
            //ArrayList<ListaCompra> lc ;
            ProdutoDataSource p = new ProdutoDataSource(SplashScreen.this);
            p.open();
            pr = p.getAllProduto();
            if ( pr.size() ==0 ) {
                p.insereProduto(new Produto("Feijao", "Zafari", 3.4));
                p.insereProduto(new Produto("Arroz", "Zafari", 2.78));
                p.insereProduto(new Produto("Batata", "Zafari", 2.41));
                p.insereProduto(new Produto("Cebola", "Zafari", 3.5));
                p.insereProduto(new Produto("Banana", "Zafari", 1.57));
                p.insereProduto(new Produto("Leite", "Zafari", 3.50));
                p.insereProduto(new Produto("Farinha trigo", "Zafari", 1.57));
                p.insereProduto(new Produto("Acucar", "Zafari", 1.57));
                p.insereProduto(new Produto("Couve", "Rissul", 1.57));
                p.insereProduto(new Produto("Catchup", "Zafari", 1.57));
                p.insereProduto(new Produto("Mostarda", "Zafari", 1.57));
                p.insereProduto(new Produto("Sazon", "Rissul", 1.57));
                p.insereProduto(new Produto("Banana", "Zafari", 1.57));

            } else {
                ListaProdutos l = new ListaProdutos();
                ArrayList<Produtos> ar = new ArrayList<>();
                int i;
                pr = p.getAllProduto();
                for ( i =0; i<pr.size();i++)
                {
                    ar.add(new Produtos(0,pr.get(i).getsNome(),pr.get(i).getPreco()));
                }
                l.setProduto(ar);

                
                /* Colocar em classe retrofit Service
                Call<ListaProdutos> call = service.enviaProdutos(l);

                call.enqueue(new Callback<ListaProdutos>() {
                    @Override
                    public void onResponse(Call<ListaProdutos> call, Response<ListaProdutos> response) {
                        int statusCode = response.code();
                        Log.d("retro",response.toString());
                        ListaProdutos user = response.body();
                    }

                    @Override
                    public void onFailure(Call<ListaProdutos> call, Throwable t) {

                    }
                });

                Call<ListaProdutos> call = service.pegaProdutos();
                call.enqueue(new Callback<ListaProdutos>() {
                    @Override
                    public void onResponse(Call<ListaProdutos> call, Response<ListaProdutos> response) {
                        int statusCode = response.code();
                        Log.d("retro",response.toString());
                        ListaProdutos user = response.body();
                    }

                    @Override
                    public void onFailure(Call<ListaProdutos> call, Throwable t) {

                    }
                });
                */
         //       l = serv.pegaProdutos();


            }
            p.close();

            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Intent i = new Intent(SplashScreen.this, Login.class);
            startActivity(i);

            // close this activity
            finish();
        }
    }

}
