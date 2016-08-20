package culik.br.com.listacompra.utils.service;

import android.app.Application;

import java.io.IOException;

import culik.br.com.listacompra.FaceApplication;
import culik.br.com.listacompra.interfaces.MyListaProdutoInterface;
import culik.br.com.listacompra.utils.model.LocalMercados;
import retrofit2.Call;

/**
 * Created by LUIZ on 23/07/2016.
 */
public class RetrofitServiceMercadoSincrono {
    final Application app = (Application) FaceApplication.getContext();
    final FaceApplication face = (FaceApplication) app;
    final MyListaProdutoInterface service = face.getService();


    private LocalMercados user = null;

    public RetrofitServiceMercadoSincrono() {
    }


    public LocalMercados pegaMercado() throws IOException {
        LocalMercados user = null;

        Call<LocalMercados> call = service.pegaMercados();
        user = call.execute().body();


        return user;

    }


}


