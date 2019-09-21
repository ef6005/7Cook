package android.example.mysevencook.main;

import android.example.mysevencook.base.BasePresenter;
import android.example.mysevencook.base.BaseView;
import android.example.mysevencook.data.Banner;
import android.example.mysevencook.data.Category;
import android.example.mysevencook.data.Recipe;

import java.util.List;

public interface MainContract {
    interface MainView extends BaseView {
        void showSlider(List<Banner> banners);

        void showCategories(List<Category> categories);

        void showRecipes(List<Recipe> recipes);
    }

    interface MainPresenter extends BasePresenter<MainView> {

    }
}
