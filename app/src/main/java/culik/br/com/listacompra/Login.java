package culik.br.com.listacompra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import culik.br.com.listacompra.utils.utils.DetectaConexao;
import culik.br.com.listacompra.utils.utils.DialogoAlerta;
import culik.br.com.listacompra.utils.utils.FbManager;

public class Login extends Activity {

    private CallbackManager callbackManager;
    private final DialogoAlerta alerta = new DialogoAlerta();
    private FbManager fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DetectaConexao cd = new DetectaConexao(this);
try {
    Boolean temInternet = cd.isConnectingToInternet();
    if (!temInternet) {
        alerta.showAlertDialog(Login.this);
        return;
    }
}
catch (Exception e){
    Log.d("Login",e.toString());

}

        callbackManager = CallbackManager.Factory.create();
        //   fb = new FbManager(this,callbackManager);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken == null) {

            LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

                @Override
                public void onSuccess(LoginResult loginResult) {

                    ChamaPrincipal();
                }

                @Override
                public void onCancel() {
                    finish();
                }

                @Override
                public void onError(FacebookException error) {
                    Log.d("CURSO", error.toString());
                }
            });
        } else {
            ChamaPrincipal();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void ChamaPrincipal() {
        Intent i = new Intent(Login.this, MainActivity.class);
        startActivity(i);
    }
}
