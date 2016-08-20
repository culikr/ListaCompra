package culik.br.com.listacompra.utils.service;

import android.app.Application;
import android.util.Log;

import java.io.IOException;

import culik.br.com.listacompra.FaceApplication;
import culik.br.com.listacompra.interfaces.MyListaProdutoInterface;
import culik.br.com.listacompra.interfaces.MyMapsInterface;
import culik.br.com.listacompra.utils.model.LocalMercados;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LUIZ on 23/07/2016.
 */
public class RetrofitServiceMercado implements Callback<LocalMercados> {
    final Application app = (Application) FaceApplication.getContext();
    final FaceApplication face = (FaceApplication) app;
    final MyListaProdutoInterface service = face.getService();
    final MyMapsInterface servMap = face.getServiceMaps();

    private LocalMercados user = null;

    public RetrofitServiceMercado() {
    }


    public LocalMercados pegaMercados() {

        Call<LocalMercados> call = service.pegaMercados();
        call.enqueue(this);

        return user;
    }

    public LocalMercados pegaMercado() {
        LocalMercados user = null;
        try {
            Call<LocalMercados> call = service.pegaMercados();

            user = call.execute().body();
        } catch (IOException e) {
            user = null;
        }
        return user;

    }

    @Override
    public void onResponse(Call<LocalMercados> call, Response<LocalMercados> response) {
        if (response.isSuccessful()) {
            int statusCode = response.code();
            Log.d("CURSO", "respose.tostring " + response.toString());
            Log.d("CURSO", "status code " + String.valueOf(statusCode));
            Log.d("CURSO", "respbody " + response.body().toString());
            //user.setMercado(response.body().getMercado());
            user = response.body();
        } else {
            Log.d("Error message", response.message());
            //For getting error code. Code is integer value like 200,404 etc
            Log.d("Error code", String.valueOf(response.code()));
        }
    }

    @Override
    public void onFailure(Call<LocalMercados> call, Throwable t) {
        Log.d("Erro Retro", t.toString());
    }

}


