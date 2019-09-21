package android.example.mysevencook.data.repo;

import android.example.mysevencook.data.Banner;
import android.example.mysevencook.service.http.HttpClient;
import android.example.mysevencook.service.http.HttpRequest;

import com.google.gson.reflect.TypeToken;

import java.util.List;

public class SliderCloudDataSource implements SliderDataSource {
    public static final String SLIDER_URL = "110sw0";
    private HttpClient httpClient;

    public SliderCloudDataSource(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public void getAll(RepositoryDataLoadCallback<List<Banner>> repositoryDataLoadCallback) {
        HttpRequest<List<Banner>> sliderRequest = new HttpRequest<>(SLIDER_URL, new TypeToken<List<Banner>>() {
        }.getType()
                , new HttpRequest.ResponseCallback<List<Banner>>() {
            @Override
            public void onSuccess(List<Banner> result) {
                repositoryDataLoadCallback.onLoad(result);
            }

            @Override
            public void onError(Exception ex) {
                repositoryDataLoadCallback.onError(ex);
            }
        });

        httpClient.sendRequest(sliderRequest);
    }
}
