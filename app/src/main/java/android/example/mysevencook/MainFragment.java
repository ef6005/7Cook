package android.example.mysevencook;

import android.example.mysevencook.adapter.CategoryAdapter;
import android.example.mysevencook.adapter.RecipesAdapter;
import android.example.mysevencook.adapter.SliderAdapter;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

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

        mSwipeRefreshLayout = getView().findViewById(R.id.main_fragment_srl_swipeRefreshLayout);
        mRecipesRv = getView().findViewById(R.id.main_fragment_rv_recipes);
        mRecipesAdapter = new RecipesAdapter(new ArrayList<Recipe>());

        mRecipesRv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecipesRv.setAdapter(mRecipesAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this::loadData);

        mCategoriesRv = getView().findViewById(R.id.main_fragment_rv_categories);
        mCategoriesRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        mCategoryAdapter = new CategoryAdapter();
        mCategoriesRv.setAdapter(mCategoryAdapter);

        mSliderRv = getView().findViewById(R.id.main_fragment_rv_slider);
        mSliderRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        SnapHelper sliderSnapHelper = new PagerSnapHelper();
        sliderSnapHelper.attachToRecyclerView(mSliderRv);

        loadData();
    }

    private void loadData() {
        //feed adapter and swipeRefreshLayout
        mSwipeRefreshLayout.setRefreshing(true);

        ApiService.getInstance(getContext().getApplicationContext()).loadSliders(new ApiServiceCallBack<List<Slider>>() {
            @Override
            public void onLoad(List<Slider> data) {
                mSliderAdapter = new SliderAdapter(data);
                mSliderRv.setAdapter(mSliderAdapter);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Exception ex) {
                Toast.makeText(getContext(), "Error Loading Banner Slider", Toast.LENGTH_SHORT).show();
            }
        });

        ApiService.getInstance(getContext().getApplicationContext()).loadCategories(new ApiServiceCallBack<List<Category>>() {
            @Override
            public void onLoad(List<Category> data) {
                mCategoryAdapter.setCategories(data);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Exception ex) {
                Toast.makeText(getContext(), "Error Loading Categories", Toast.LENGTH_SHORT).show();
            }
        });

        ApiService.getInstance(getContext().getApplicationContext()).loadRecipes(new ApiServiceCallBack<List<Recipe>>() {
            @Override
            public void onLoad(List<Recipe> data) {
                mRecipesAdapter.setRecipes(data);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Exception ex) {
                Toast.makeText(getContext(), "Error Loading Recipes", Toast.LENGTH_LONG).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
