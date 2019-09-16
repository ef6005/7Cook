package android.example.mysevencook.service.image;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public final class PicassoImageLoadingService implements ImageLoadingService {
    @Override
    public void loadImage(String url, ImageView imageView) {
        Picasso.get().load(url).into(imageView);
    }
}
