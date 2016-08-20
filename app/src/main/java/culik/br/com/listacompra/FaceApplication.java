package culik.br.com.listacompra;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;

import com.facebook.FacebookSdk;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import culik.br.com.listacompra.interfaces.MyListaProdutoInterface;
import culik.br.com.listacompra.interfaces.MyMapsInterface;
import culik.br.com.listacompra.utils.model.Config;
import culik.br.com.listacompra.utils.utils.LoggingInteceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
/**
 * Created by LUIZ on 10/06/2016.
 */
public class FaceApplication extends Application {
    private Config config;
    private SharedPreferences mSharedPreferences;
    private MyListaProdutoInterface serviceProduto;
    private static Context mContext;
    private MyMapsInterface serviceMaps;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        FacebookSdk.sdkInitialize(getApplicationContext());
        mSharedPreferences = getSharedPreferences("ListaCompra", MODE_PRIVATE);
        config =getConfig();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor( new LoggingInteceptor() ).build();
        OkHttpClient client2 = new OkHttpClient.Builder().addInterceptor( logging).build();
        Gson gson = new GsonBuilder().create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://culik.ddns.net:8080/ListaProdutos/rest/produto/")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client2)
                .build();
        Retrofit retro = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client2)
                .build();

        serviceProduto = retrofit.create(MyListaProdutoInterface.class);
        serviceMaps = retro.create(MyMapsInterface.class);

    }

    public Config getConfig() {
        if (config == null) {
            boolean useFaceBook = mSharedPreferences.getBoolean("useFacebook", true);
            boolean sendEmail = mSharedPreferences.getBoolean("sendEmail", true);
            boolean sendWhats = mSharedPreferences.getBoolean("sendWhats", true);
            boolean sendSms = mSharedPreferences.getBoolean("sendSms", true);
            String  cabecSms =  mSharedPreferences.getString(  "sendSms",    "Por favor traga os seguintes items do mercado");
            String  rodapeSMS=  mSharedPreferences.getString(  "rodapeSMS"  , "Obrigado(a)");
            String  cabecEmail=  mSharedPreferences.getString( "cabecEmail", "Por favor traga os seguintes items do mercado");
            String  rodapeEmail=  mSharedPreferences.getString("rodapeEmail", "Obrigado(a)");
            boolean sendProdutoAuto  = mSharedPreferences.getBoolean("sendProdutoAuto",false);
            boolean sendMercadoAutop = mSharedPreferences.getBoolean("sendMercadoAutop",false);

            config = new Config(useFaceBook, sendEmail, sendWhats, sendSms,cabecSms,rodapeSMS,cabecEmail,rodapeEmail,sendProdutoAuto,sendMercadoAutop);
        }
        return config;
    }

    public void setConfig(Config configs) {
        config = configs;
        SharedPreferences.Editor e = mSharedPreferences.edit();

        e.putBoolean("useFacebook", config.isUseFaceBook());
        e.putBoolean("sendEmail", config.isSendEmail());
        e.putBoolean("sendWhats", config.isSendWhats());
        e.putBoolean("sendSms", config.isSendSms());
        e.putString("sendSms",    config.getCabecSms());
        e.putString("rodapeSMS"  ,config.getRodapeSMS());
        e.putString("cabecEmail", config.getCabecEmail());
        e.putString("rodapeEmail",config.getRodapeEmail());
        e.putBoolean("sendProdutoAuto",config.isSendProdutoAuto());
        e.putBoolean("sendMercadoAutop",config.isSendMercadoAutop());
        e.commit();

    }

    public  MyListaProdutoInterface getService(){
        return serviceProduto;
    }

    public MyMapsInterface getServiceMaps() {
        return serviceMaps;
    }
    public static Context getContext(){
        return mContext;
    }
}


