package android.example.mysevencook;

import android.example.mysevencook.service.ServiceProvider;
import android.example.mysevencook.service.image.ImageLoadingService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    List<Recipe> mRecipes = new ArrayList<>();

    public RecipeAdapter(List<Recipe> recipes) {
        mRecipes = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.bindRecipe(mRecipes.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageIv;
        private TextView titleTv, descriptionTv, authorTv;
        private RatingBar ratingRb;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            imageIv = itemView.findViewById(R.id.recipe_iv_image);
            titleTv = itemView.findViewById(R.id.recipe_tv_title);
            descriptionTv = itemView.findViewById(R.id.recipe_tv_description);
            authorTv = itemView.findViewById(R.id.recipe_tv_author);
            ratingRb = itemView.findViewById(R.id.recipe_rb_rating);
        }

        public void bindRecipe(Recipe recipe) {
            ImageLoadingService imageLoadingService = ServiceProvider.provideImageLoadingService();

            imageLoadingService.loadImage(recipe.getImageUrl(), imageIv);
            titleTv.setText(recipe.getTitle());
            descriptionTv.setText(recipe.getDescription());
            authorTv.setText(recipe.getAuthor());
            ratingRb.setRating(recipe.getRating());
        }
    }
}
