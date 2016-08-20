package culik.br.com.listacompra.utils.service;

import android.app.Application;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import culik.br.com.listacompra.FaceApplication;
import culik.br.com.listacompra.interfaces.MyListaProdutoInterface;
import culik.br.com.listacompra.interfaces.MyMapsInterface;
import culik.br.com.listacompra.utils.model.ListaProdutos;
import culik.br.com.listacompra.utils.model.LocalMercados;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LUIZ on 23/07/2016.
 */
public class RetrofitService {
    final Application app = (Application) FaceApplication.getContext();
    final FaceApplication face = (FaceApplication) app;
    final MyListaProdutoInterface service = face.getService();
    final MyMapsInterface servMap = face.getServiceMaps();


    public RetrofitService() {
    }

    public void enviaListaProduto(ListaProdutos p) {
        Call<ListaProdutos> call = service.enviaProdutos(p);

        call.enqueue(new Callback<ListaProdutos>() {
            @Override
            public void onResponse(Call<ListaProdutos> call, Response<ListaProdutos> response) {
                int statusCode = response.code();
                Log.d("CURSO", response.toString());
                ListaProdutos user = response.body();
            }

            @Override
            public void onFailure(Call<ListaProdutos> call, Throwable t) {

            }
        });

    }

    public ListaProdutos pegaProdutos() {
        final ListaProdutos[] user = new ListaProdutos[1];
        Call<ListaProdutos> call = service.pegaProdutos();
        call.enqueue(new Callback<ListaProdutos>() {
            @Override
            public void onResponse(Call<ListaProdutos> call, Response<ListaProdutos> response) {
                int statusCode = response.code();
                Log.d("CURSO", response.toString());
                user[0] = response.body();
            }

            @Override
            public void onFailure(Call<ListaProdutos> call, Throwable t) {
                Log.d("Erro Retro", t.toString());
            }
        });
        return user[0];
    }

    public LocalMercados pegaMercados() {
        final LocalMercados[] user = new LocalMercados[1];
        Call<LocalMercados> call = service.pegaMercados();
        call.enqueue(new Callback<LocalMercados>() {
            @Override
            public void onResponse(Call<LocalMercados> call, Response<LocalMercados> response) {
                int statusCode = response.code();
                Log.d("CURSO", response.toString());
                user[0] = response.body();
            }

            @Override
            public void onFailure(Call<LocalMercados> call, Throwable t) {
                Log.d("Erro Retro", t.toString());
            }
        });
        return user[0];
    }


    public JSONObject procuraEndereco(String address) {
        final JSONObject[] jo = {null};
        Call<ResponseBody> call = servMap.procuraEndereco(address, "AIzaSyBXu8WS6ycWI9e4oNsGM-xpnfDg9zofA-k");
        call.enqueue(new Callback<ResponseBody>() {
                         @Override
                         public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                             try {
                                 jo[0] = new JSONObject(response.body().string());

                             } catch (JSONException e) {
                                 e.printStackTrace();
                             } catch (IOException e) {
                                 e.printStackTrace();
                             }


                         }

                         @Override
                         public void onFailure(Call<ResponseBody> call, Throwable t) {
                             Log.d("Erro Retro", t.toString());
                         }
                     }

        );
        return jo[0];
    }
}


