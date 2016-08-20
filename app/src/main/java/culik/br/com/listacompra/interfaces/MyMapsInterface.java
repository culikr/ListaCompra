package culik.br.com.listacompra.interfaces;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by LUIZ on 14/08/2016.
 */
public interface MyMapsInterface {
    @GET("/maps/api/geocode/json?sensor=false")
    Call<ResponseBody> procuraEndereco(@Query("address") String address, @Query("key") String Key);

}
