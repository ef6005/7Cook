package android.example.mysevencook.service;

import android.example.mysevencook.service.image.ImageLoadingService;
import android.example.mysevencook.service.image.PicassoImageLoadingService;

public class ServiceProvider {

    public static ImageLoadingService provideImageLoadingService() {
        return new PicassoImageLoadingService();
    }
}
