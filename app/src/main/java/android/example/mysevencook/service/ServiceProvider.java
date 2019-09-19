package android.example.mysevencook.service;

import android.content.Context;
import android.example.mysevencook.service.http.HttpClient;
import android.example.mysevencook.service.http.VolleyHttpClient;
import android.example.mysevencook.service.image.ImageLoadingService;
import android.example.mysevencook.service.image.PicassoImageLoadingService;

public class ServiceProvider {

    private static HttpClient httpClient;

    public static ImageLoadingService provideImageLoadingService() {
        return new PicassoImageLoadingService();
    }

    public static HttpClient provideHttpClient(Context context) {
        if (httpClient == null)
            httpClient = new VolleyHttpClient(context.getApplicationContext());

        return httpClient;
    }
}
