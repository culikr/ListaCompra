package culik.br.com.listacompra.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;

import culik.br.com.listacompra.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class LeituraCodBarras extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    // --Commented out by Inspection (21/08/2016 01:39):Integer response = 0;
    // --Commented out by Inspection (21/08/2016 01:39):public static final int PERMISSION_REQUEST_CAMERA = 1;
    public static final String TAG = "LeituraCodBarras";

    /**
     * Id to identify a camera permission request.
     */
    private static final int REQUEST_CAMERA = 0;
    @Override

    public void onCreate(Bundle state) {


        super.onCreate(state);
        setContentView(R.layout.activity_scanner);


        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        ArrayList<BarcodeFormat> formats = new ArrayList<>();
        formats.add(BarcodeFormat.EAN_13);
        formats.add(BarcodeFormat.EAN_8);
        mScannerView = new ZXingScannerView(this);
        mScannerView.setFormats(formats);
        contentFrame.addView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();

        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {

        Intent data = new Intent();
        mScannerView.resumeCameraPreview(this);
        Log.e("CODBAR", rawResult.getText());
        data.putExtra("name", rawResult.getText());

        setResult(RESULT_OK, data);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.resumeCameraPreview(LeituraCodBarras.this);
            }
        }, 2000);
        finish();
    }

    /*@Override
    public void onDestroy() {
        super.onDestroy();
        if ( mScannerView != null)
        mScannerView.stopCamera();
        mScannerView = null;


    }*/



}