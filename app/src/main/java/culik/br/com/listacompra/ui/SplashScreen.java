package culik.br.com.listacompra.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import culik.br.com.listacompra.Login;
import culik.br.com.listacompra.R;
import culik.br.com.listacompra.utils.database.MercadoDataSource;
import culik.br.com.listacompra.utils.database.ProdutoDataSource;
import culik.br.com.listacompra.utils.model.ListaProdutos;
import culik.br.com.listacompra.utils.model.LocalMercados;
import culik.br.com.listacompra.utils.model.Mercados;
import culik.br.com.listacompra.utils.model.Produto;
import culik.br.com.listacompra.utils.model.Produtos;
import culik.br.com.listacompra.utils.service.RetrofitServiceMercadoSincrono;
import culik.br.com.listacompra.utils.service.RetrofitServiceProdutoSincrono;

public class SplashScreen extends Activity {


    // --Commented out by Inspection (21/08/2016 01:40):private static int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new CarregaDadosAsync().execute();

    }

    private class CarregaDadosAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<Produto> pr;
            ArrayList<Mercados> mr;
            ListaProdutos lp;
            RetrofitServiceProdutoSincrono serv = new RetrofitServiceProdutoSincrono();
            RetrofitServiceMercadoSincrono serv1 = new RetrofitServiceMercadoSincrono();
            LocalMercados lm;
            //JSONObject jo = serv.procuraEndereco("Rua Lacy pereira figueiro 100, São Leopoldo");
            //ArrayList<ListaCompra> lc ;
            MercadoDataSource m = new MercadoDataSource(SplashScreen.this);
            m.open();
            mr = m.getAllMercados();
            if (mr.size() == 0) {
                try {
                    lm = serv1.pegaMercado();

                    for (int i = 0; i < lm.getMercado().size(); i++) {

                        m.insereMercados(lm.getMercado().get(i));
                    }
                } catch (IOException e) {
                    //e.printStackTrace();

                    m.insereMercados(new Mercados(1, -30.0275, -51.2278, "Mercado Publico Porto Alegre"));
                    m.insereMercados(new Mercados(2, -29.76778, -51.138958, "Bourbon São Leopoldo"));
                    m.insereMercados(new Mercados(3, -29.769186, -51.132199, "Rissul Rio Branco"));
                    m.insereMercados(new Mercados(4, -29.764082, -51.139237, "Big São Leopoldo"));
                    m.insereMercados(new Mercados(5, -29.769215, -51.143046, "Nacional Centro São Leopoldo"));
                    m.insereMercados(new Mercados(6, -29.78702, -51.131333, "Supermercado Mello São Leopoldo"));
                    m.insereMercados(new Mercados(7, -29.791592, -51.134273, "Supermercado Santa Tereza - REDEFORT"));

                }
            }

            ProdutoDataSource p = new ProdutoDataSource(SplashScreen.this);
            p.open();
            pr = p.getAllProduto();
            if (pr.size() == 0) {
                try {
                    lp = serv.pegaProdutos();

                    for (int i = 0; i < lp.getProduto().size(); i++) {
                        Produtos p1 = lp.getProduto().get(i);

                        p.insereProduto(new Produto(p1.getNome(), p1.getIdMercado(), p1.getPreco(), p1.getCodbar()));
                    }


                } catch (IOException e) {
                    p.insereProduto(new Produto(1, "Winna vinagre de alcool", 2, 1.69, "7896407500358"));
                    p.insereProduto(new Produto(2, "Vinho tinto de mesa suave Del Bom", 2, 7.99, "7898930317434"));

                }

            }

                /*
                p.insereProduto(new Produto("Feijao", "Zafari", 3.4,''));
                p.insereProduto(new Produto("Arroz", "Zafari", 2.78,''));
                p.insereProduto(new Produto("Batata", "Zafari", 2.41,''));
                p.insereProduto(new Produto("Cebola", "Zafari", 3.5,''));
                p.insereProduto(new Produto("Banana", "Zafari", 1.57,''));
                p.insereProduto(new Produto("Leite", "Zafari", 3.50,''));
                p.insereProduto(new Produto("Farinha trigo", "Zafari", 1.57,''));
                p.insereProduto(new Produto("Acucar", "Zafari", 1.57,''));
                p.insereProduto(new Produto("Couve", "Rissul", 1.57,''));
                p.insereProduto(new Produto("Catchup", "Zafari", 1.57,''));
                p.insereProduto(new Produto("Mostarda", "Zafari", 1.57,''));
                p.insereProduto(new Produto("Sazon", "Rissul", 1.57,''));
                p.insereProduto(new Produto("Banana", "Zafari", 1.57,''));


            } else {
                ListaProdutos l = new ListaProdutos();
                ArrayList<Produtos> ar = new ArrayList<>();
                int i;
                pr = p.getAllProduto();
                for ( i =0; i<pr.size();i++)
                {
                    ar.add(new Produtos(0,pr.get(i).getNome(),pr.get(i).getPreco()));
                }
                l.setProduto(ar);

                */
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

         //       l = serv.pegaProdutos();


            }
        */
            try {
                p.close();
            }
            catch (Exception e ) {
                Log.d("Login", e.toString());
            }
            try {
            m.close();
            }
            catch (Exception e ) {
                Log.d("Login", e.toString());
            }


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
