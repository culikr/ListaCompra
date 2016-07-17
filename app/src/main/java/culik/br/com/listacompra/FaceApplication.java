package culik.br.com.listacompra;

import android.app.Application;
import android.content.SharedPreferences;

import com.facebook.FacebookSdk;

import culik.br.com.listacompra.utils.model.Config;

/**
 * Created by LUIZ on 10/06/2016.
 */
public class FaceApplication extends Application {
    public Config config;
    private SharedPreferences mSharedPreferences;


    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        mSharedPreferences = getSharedPreferences("ListaCompra", MODE_PRIVATE);
        config =getConfig();
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

            config = new Config(useFaceBook, sendEmail, sendWhats, sendSms,cabecSms,rodapeSMS,cabecEmail,rodapeEmail);
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
        e.commit();

    }
}


