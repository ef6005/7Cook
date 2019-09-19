package android.example.mysevencook;

import android.example.mysevencook.adapter.CategoryAdapter;
import android.example.mysevencook.adapter.RecipesAdapter;
import android.example.mysevencook.adapter.SliderAdapter;
import android.example.mysevencook.data.Category;
import android.example.mysevencook.data.Recipe;
import android.example.mysevencook.data.Slider;
import android.example.mysevencook.data.repo.CategoryCloudDataSource;
import android.example.mysevencook.data.repo.CategoryRepository;
import android.example.mysevencook.data.repo.RecipeCloudDataSource;
import android.example.mysevencook.data.repo.RecipeRepository;
import android.example.mysevencook.data.repo.RepositoryDataLoadCallback;
import android.example.mysevencook.data.repo.SliderCloudDataSource;
import android.example.mysevencook.data.repo.SliderRepository;
import android.example.mysevencook.service.ServiceProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

public class MainFragment extends Fragment {
    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private SliderRepository sliderRepository;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecipesRv;
    private RecipesAdapter mRecipesAdapter;
    private RecyclerView mCategoriesRv;
    private CategoryAdapter mCategoryAdapter;
    private RecyclerView mSliderRv;
    private SliderAdapter mSliderAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recipeRepository = new RecipeRepository(new RecipeCloudDataSource(ServiceProvider.provideHttpClient(getContext())));
        categoryRepository = new CategoryRepository(new CategoryCloudDataSource(ServiceProvider.provideHttpClient(getContext())));
        sliderRepository = new SliderRepository(new SliderCloudDataSource(ServiceProvider.provideHttpClient(getContext())));

        mSwipeRefreshLayout = getView().findViewById(R.id.main_fragment_srl_swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this::loadData);

        mRecipesRv = getView().findViewById(R.id.main_fragment_rv_recipes);
        mRecipesRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        mCategoriesRv = getView().findViewById(R.id.main_fragment_rv_categories);
        mCategoriesRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        mSliderRv = getView().findViewById(R.id.main_fragment_rv_slider);
        mSliderRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        SnapHelper sliderSnapHelper = new PagerSnapHelper();
        sliderSnapHelper.attachToRecyclerView(mSliderRv);

        loadData();
    }

    private void loadData() {
        //feed adapter and swipeRefreshLayout
        mSwipeRefreshLayout.setRefreshing(true);

        sliderRepository.getAll(new RepositoryDataLoadCallback<List<Slider>>() {
            @Override
            public void onLoad(List<Slider> data) {
                mSliderAdapter = new SliderAdapter(data);
                mSliderRv.setAdapter(mSliderAdapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Exception ex) {
                Toast.makeText(getContext(), "Error Loading Banner Slider", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        categoryRepository.getAll(new RepositoryDataLoadCallback<List<Category>>() {
            @Override
            public void onLoad(List<Category> data) {
                mCategoryAdapter = new CategoryAdapter(data);
                mCategoriesRv.setAdapter(mCategoryAdapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Exception ex) {
                Toast.makeText(getContext(), "Error Loading Categories", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
//
        recipeRepository.getAll(new RepositoryDataLoadCallback<List<Recipe>>() {
            @Override
            public void onLoad(List<Recipe> data) {
                mRecipesAdapter = new RecipesAdapter(data);
                mRecipesRv.setAdapter(mRecipesAdapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Exception ex) {
                Toast.makeText(getContext(), "Error Loading Recipes", Toast.LENGTH_LONG).show();
                Log.e("hamid", "onError: " + ex.getMessage(), ex);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
