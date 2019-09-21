package android.example.mysevencook.main;

import android.example.mysevencook.R;
import android.example.mysevencook.adapter.BannerAdapter;
import android.example.mysevencook.adapter.CategoryAdapter;
import android.example.mysevencook.adapter.RecipesAdapter;
import android.example.mysevencook.base.BaseFragment;
import android.example.mysevencook.data.Banner;
import android.example.mysevencook.data.Category;
import android.example.mysevencook.data.Recipe;
import android.example.mysevencook.data.repo.CategoryCloudDataSource;
import android.example.mysevencook.data.repo.CategoryRepository;
import android.example.mysevencook.data.repo.RecipeCloudDataSource;
import android.example.mysevencook.data.repo.RecipeRepository;
import android.example.mysevencook.data.repo.SliderCloudDataSource;
import android.example.mysevencook.data.repo.SliderRepository;
import android.example.mysevencook.service.ServiceProvider;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

public class MainFragment extends BaseFragment implements MainContract.MainView {
    private MainContract.MainPresenter mainPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mainPresenter = new MainPresenter(
                new SliderRepository(new SliderCloudDataSource(ServiceProvider.provideHttpClient(getContext())))
                , new CategoryRepository(new CategoryCloudDataSource(ServiceProvider.provideHttpClient(getContext())))
                , new RecipeRepository(new RecipeCloudDataSource(ServiceProvider.provideHttpClient(getContext()))));

        mainPresenter.onAttach(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainPresenter.onDetach();
    }

    @Override
    public void showSlider(List<Banner> banners) {
        RecyclerView sliderRv = getView().findViewById(R.id.main_fragment_rv_slider);
        sliderRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        BannerAdapter bannerAdapter = new BannerAdapter(banners);
        sliderRv.setAdapter(bannerAdapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(sliderRv);
    }

    @Override
    public void showCategories(List<Category> categories) {
        RecyclerView categoriesRv = getView().findViewById(R.id.main_fragment_rv_categories);
        categoriesRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        categoriesRv.setAdapter(new CategoryAdapter(categories));
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        RecyclerView recipesRv = getView().findViewById(R.id.main_fragment_rv_recipes);
        recipesRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recipesRv.setAdapter(new RecipesAdapter(recipes));
    }

    @Override
    public void setRefreshingProgressBar(boolean refreshing) {
        SwipeRefreshLayout swipeRefreshLayout = getView().findViewById(R.id.main_fragment_srl_swipeRefreshLayout);
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
