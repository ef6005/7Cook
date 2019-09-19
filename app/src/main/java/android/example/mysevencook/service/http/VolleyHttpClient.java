package android.example.mysevencook.service.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyHttpClient implements HttpClient {
    private RequestQueue requestQueue;

    public VolleyHttpClient(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public <T> void sendRequest(HttpRequest<T> httpRequest) {
        GsonRequest<T> volleyRequest = new GsonRequest<>(convertToVolleyMethod(httpRequest.getMethod())
                , httpRequest.getUrl()
                , volleyError -> httpRequest.getResponseCallback().onError(volleyError)
                , data -> httpRequest.getResponseCallback().onSuccess(data)
                , httpRequest.getType());

        requestQueue.add(volleyRequest);
    }

    private int convertToVolleyMethod(HttpRequest.Method method) {
        switch (method) {
            case GET:
                return Request.Method.GET;
            case POST:
                return Request.Method.POST;
        }
        return Request.Method.GET;
    }

//    @Override
//    public String getBaseUrl() {
//        return "";
//    }

    @Override
    public void cancel() {

    }

    @Override
    public void cancelAll() {

    }
}
