package android.example.mysevencook.adapter;

import android.example.mysevencook.R;
import android.example.mysevencook.data.Banner;
import android.example.mysevencook.service.ServiceProvider;
import android.example.mysevencook.service.image.ImageLoadingService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.SliderViewHolder> {

    private List<Banner> banners;

    public BannerAdapter(List<Banner> banners) {
        //fix api bug on banners
        if (banners.size() > 2) {
            Banner temp = banners.get(0);
            banners.set(0, banners.get(1));
            banners.set(1, temp);
            temp = banners.get(1);
            banners.set(1, banners.get(2));
            banners.set(2, temp);
            banners.remove(2);
        }
        this.banners = banners;
    }



    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.bindSlider(banners.get(position));
    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {

        private ImageView bannerIv;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerIv = itemView.findViewById(R.id.item_slider_iv_banner);
        }

        public void bindSlider(Banner banner) {
            ImageLoadingService imageLoadingService = ServiceProvider.provideImageLoadingService();
            imageLoadingService.loadImage(banner.getImageUrl(), bannerIv);
        }
    }
}
