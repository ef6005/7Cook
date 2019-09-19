package android.example.mysevencook.data.repo;

import android.example.mysevencook.data.Category;
import android.example.mysevencook.service.http.HttpClient;
import android.example.mysevencook.service.http.HttpRequest;

import com.google.gson.reflect.TypeToken;

import java.util.List;

public class CategoryCloudDataSource implements CategoryDataSource {
    public static final String CATEGORY_URL = "v0bog";
    private HttpClient httpClient;

    public CategoryCloudDataSource(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public void getAll(RepositoryDataLoadCallback<List<Category>> repositoryDataLoadCallback) {
        HttpRequest<List<Category>> categoryRequest = new HttpRequest<>(CATEGORY_URL
                , new TypeToken<List<Category>>() {
        }.getType()
                , new HttpRequest.ResponseCallback<List<Category>>() {
            @Override
            public void onSuccess(List<Category> result) {
                repositoryDataLoadCallback.onLoad(result);
            }

            @Override
            public void onError(Exception ex) {
                repositoryDataLoadCallback.onError(ex);
            }
        });

        httpClient.sendRequest(categoryRequest);
    }
}
