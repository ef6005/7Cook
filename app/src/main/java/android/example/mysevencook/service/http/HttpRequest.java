package android.example.mysevencook.service.http;

import java.lang.reflect.Type;

public class HttpRequest<T> {
    public static final String BASE_URL = "https://api.myjson.com/bins/";

    public enum Method {
        GET, POST
    }

    public interface ResponseCallback<T> {
        void onSuccess(T result);

        void onError(Exception ex);
    }

    private Method method;
    private String url;
    private Type type;
    private ResponseCallback<T> responseCallback;

    public HttpRequest(String url, Type type, ResponseCallback<T> responseCallback) {
        this(Method.GET, url, type, responseCallback);
    }

    public HttpRequest(Method method, String url, Type type, ResponseCallback<T> responseCallback) {
        this.method = method;
        this.url = url;
        this.type = type;
        this.responseCallback = responseCallback;
    }

    public String getUrl() {
        return BASE_URL + url;
    }

    public Type getType() {
        return type;
    }

    public ResponseCallback<T> getResponseCallback() {
        return responseCallback;
    }

    public Method getMethod() {
        return method;
    }
}
