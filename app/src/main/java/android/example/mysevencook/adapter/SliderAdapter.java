package android.example.mysevencook.adapter;

import android.example.mysevencook.R;
import android.example.mysevencook.data.Slider;
import android.example.mysevencook.service.ServiceProvider;
import android.example.mysevencook.service.image.ImageLoadingService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private List<Slider> sliders;

    public SliderAdapter(List<Slider> sliders) {
        this.sliders = sliders;
    }

    public void setSliders(List<Slider> sliders) {
        this.sliders = sliders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.bindSlider(sliders.get(position));
    }

    @Override
    public int getItemCount() {
        return sliders.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {

        private ImageView bannerIv;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            bannerIv = itemView.findViewById(R.id.item_slider_iv_banner);
        }

        public void bindSlider(Slider slider) {
            ImageLoadingService imageLoadingService = ServiceProvider.provideImageLoadingService();
            imageLoadingService.loadImage(slider.getImageUrl(), bannerIv);
        }
    }
}
