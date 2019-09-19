package android.example.mysevencook.service.http;


import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonRequest<T> extends Request<T> {

    private final Response.Listener<T> responseListener;
    private final Type typeOfT;

    public GsonRequest(int method, String url, @Nullable Response.ErrorListener listener
            , Response.Listener<T> responseListener, Type typeOfT) {
        super(method, url, listener);
        this.responseListener = responseListener;
        this.typeOfT = typeOfT;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try {
            Gson gson = new Gson();
            String responseStr = new String(response.data);
            T result = gson.fromJson(responseStr, typeOfT);
            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new VolleyError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        responseListener.onResponse(response);
    }
}
