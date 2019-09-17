package android.example.mysevencook;

public interface ApiServiceCallBack<T> {
    public void onLoad(T data);

    public void onError(Exception ex);
}
