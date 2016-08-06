package culik.br.com.listacompra.utils.service;

import android.app.Application;
import android.util.Log;

import culik.br.com.listacompra.FaceApplication;
import culik.br.com.listacompra.interfaces.MyListaProdutoInterface;
import culik.br.com.listacompra.utils.model.ListaProdutos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LUIZ on 23/07/2016.
 */
public class RetrofitService {
    final Application app =  (Application)FaceApplication.getContext();
    final FaceApplication face = (FaceApplication) app;
    final MyListaProdutoInterface service = face.getService();

    public RetrofitService() {}
    public void enviaListaProduto(ListaProdutos p ){
        Call<ListaProdutos> call = service.enviaProdutos(p);

        call.enqueue(new Callback<ListaProdutos>() {
            @Override
            public void onResponse(Call<ListaProdutos> call, Response<ListaProdutos> response) {
                int statusCode = response.code();
                Log.d("CURSO",response.toString());
                ListaProdutos user = response.body();
            }

            @Override
            public void onFailure(Call<ListaProdutos> call, Throwable t) {

            }
        });

    }
    public ListaProdutos pegaProdutos(){
        final ListaProdutos[] user = new ListaProdutos[1];
        Call<ListaProdutos> call = service.pegaProdutos();
        call.enqueue(new Callback<ListaProdutos>() {
            @Override
            public void onResponse(Call<ListaProdutos> call, Response<ListaProdutos> response) {
                int statusCode = response.code();
                Log.d("CURSO",response.toString());
                 user[0] = response.body();
            }

            @Override
            public void onFailure(Call<ListaProdutos> call, Throwable t) {

            }
        });
     return user[0];
    }
}
