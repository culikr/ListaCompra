package culik.br.com.listacompra.utils.utils;

/**
 * Created by LUIZ on 21/07/2016.
 */

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by LUIZ on 19/06/2016.
 */
public class LoggingInteceptor implements Interceptor {
    @Override
    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.e("CURSO", String.format("Sending request %s on %s e corpo %s",
                request.url(), request.headers(),bodyToString(request)));
        okhttp3.Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.e("CURSO", String.format("Received response for %s in %.1fms    \n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        final String responseString = new String(response.body().bytes());

        Log.e("CURSO", "Response: " + responseString);

        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), responseString))
                .build();
    }
    private static String bodyToString(final Request request){

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
