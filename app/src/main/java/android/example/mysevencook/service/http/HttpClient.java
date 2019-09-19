package android.example.mysevencook.service.http;

public interface HttpClient {
    <T> void sendRequest(HttpRequest<T> httpRequest);
    //   String getBaseUrl();

    void cancel();

    void cancelAll();
}
