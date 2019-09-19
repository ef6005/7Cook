package android.example.mysevencook.data.repo;

import android.example.mysevencook.data.Slider;
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
    public void getAll(RepositoryDataLoadCallback<List<Slider>> repositoryDataLoadCallback) {
        HttpRequest<List<Slider>> sliderRequest = new HttpRequest<>(SLIDER_URL, new TypeToken<List<Slider>>() {
        }.getType()
                , new HttpRequest.ResponseCallback<List<Slider>>() {
            @Override
            public void onSuccess(List<Slider> result) {
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
