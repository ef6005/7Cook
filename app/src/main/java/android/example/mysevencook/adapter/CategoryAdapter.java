package android.example.mysevencook.adapter;

import android.example.mysevencook.Category;
import android.example.mysevencook.R;
import android.example.mysevencook.service.ServiceProvider;
import android.example.mysevencook.service.image.ImageLoadingService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categories = new ArrayList<>();

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bindCategory(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView categoryImageCiv;
        private TextView titleTv;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImageCiv = itemView.findViewById(R.id.category_item_civ_image);
            titleTv = itemView.findViewById(R.id.category_item_tv_title);
        }

        public void bindCategory(Category category) {
            titleTv.setText(category.getTitle());
            ImageLoadingService imageLoadingService = ServiceProvider.provideImageLoadingService();
            imageLoadingService.loadImage(category.getImageUrl(), categoryImageCiv);
        }
    }
}
