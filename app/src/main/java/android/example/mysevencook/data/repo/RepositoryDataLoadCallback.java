package android.example.mysevencook.data.repo;

public interface RepositoryDataLoadCallback<T> {
    public void onLoad(T data);

    public void onError(Exception ex);
}
