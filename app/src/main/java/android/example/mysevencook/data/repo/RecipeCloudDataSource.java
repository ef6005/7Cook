package android.example.mysevencook.data.repo;

import android.example.mysevencook.data.Recipe;
import android.example.mysevencook.service.http.HttpClient;
import android.example.mysevencook.service.http.HttpRequest;

import com.google.gson.reflect.TypeToken;

import java.util.List;

public class RecipeCloudDataSource implements RecipeDataSource {
    public static String RECIPE_URL = "n7bxs";
    HttpClient httpClient;

    public RecipeCloudDataSource(HttpClient httpClient) {

        this.httpClient = httpClient;
    }

    @Override
    public void getAll(RepositoryDataLoadCallback<List<Recipe>> repositoryDataLoadCallback) {

        HttpRequest<List<Recipe>> recipeHttpRequest =
                new HttpRequest<>(RECIPE_URL
                        , new TypeToken<List<Recipe>>() {
                }.getType()
                        , new HttpRequest.ResponseCallback<List<Recipe>>() {
                    @Override
                    public void onSuccess(List<Recipe> result) {
                        repositoryDataLoadCallback.onLoad(result);
                    }

                    @Override
                    public void onError(Exception ex) {
                        repositoryDataLoadCallback.onError(ex);
                    }
                });

        httpClient.sendRequest(recipeHttpRequest);
    }
}
