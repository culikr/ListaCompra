package culik.br.com.listacompra.utils.service;

import android.app.Application;

import org.json.JSONObject;

import java.io.IOException;

import culik.br.com.listacompra.FaceApplication;
import culik.br.com.listacompra.interfaces.MyListaProdutoInterface;
import culik.br.com.listacompra.interfaces.MyMapsInterface;
import culik.br.com.listacompra.utils.model.ListaProdutos;
import retrofit2.Call;

/**
 * Created by LUIZ on 23/07/2016.
 */
public class RetrofitServiceProdutoSincrono {
    final Application app = (Application) FaceApplication.getContext();
    final FaceApplication face = (FaceApplication) app;
    final MyListaProdutoInterface service = face.getService();
    final MyMapsInterface servMap = face.getServiceMaps();


    public RetrofitServiceProdutoSincrono() {
    }

    public ListaProdutos pegaProdutos() throws IOException {
        ListaProdutos prod = null;
        Call<ListaProdutos> call = service.pegaProdutos();
        prod = call.execute().body();

        return prod;
    }

    public JSONObject procuraEndereco(String address) throws IOException {
        JSONObject ret = null;
        Call<JSONObject> call = servMap.procuraEnderecos(address,"AIzaSyBXu8WS6ycWI9e4oNsGM-xpnfDg9zofA-k");
        ret = call.execute().body();

        return ret;
    }

}


