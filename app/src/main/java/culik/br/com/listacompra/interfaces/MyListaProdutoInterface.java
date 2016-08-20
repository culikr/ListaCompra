package culik.br.com.listacompra.interfaces;

/**
 * Created by LUIZ on 17/07/2016.
 */

import culik.br.com.listacompra.utils.model.ListaProdutos;
import culik.br.com.listacompra.utils.model.LocalMercados;
import culik.br.com.listacompra.utils.model.Produto;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface MyListaProdutoInterface {

    @GET("id")
    Call<ResponseBody> pegaProduto(@Query("id") long id);

    @GET("all")
    Call<ListaProdutos> pegaProdutos();

    @PUT
    Call<ResponseBody> enviaProduto(Produto[] pr);

    @POST("insereprodutos")
    Call<ListaProdutos> enviaProdutos(@Body ListaProdutos p);


    @GET("mercados")
    Call<LocalMercados> pegaMercados();

}
