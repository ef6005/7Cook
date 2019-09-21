package android.example.mysevencook.main;

import android.example.mysevencook.data.Banner;
import android.example.mysevencook.data.Category;
import android.example.mysevencook.data.Recipe;
import android.example.mysevencook.data.repo.CategoryRepository;
import android.example.mysevencook.data.repo.RecipeRepository;
import android.example.mysevencook.data.repo.RepositoryDataLoadCallback;
import android.example.mysevencook.data.repo.SliderRepository;

import java.util.List;

public class MainPresenter implements MainContract.MainPresenter {
    private MainContract.MainView mainView;
    private SliderRepository sliderRepository;
    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;

    public MainPresenter(SliderRepository sliderRepository, CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.sliderRepository = sliderRepository;
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void attachView(MainContract.MainView view) {
        this.mainView = view;

        mainView.setRefreshingProgressBar(true);

        loadSlider();
        loadCategories();
        loadRecipes();
    }

    private void loadRecipes() {
        recipeRepository.getAll(new RepositoryDataLoadCallback<List<Recipe>>() {
            @Override
            public void onLoad(List<Recipe> data) {
                if (mainView != null) {
                    mainView.showRecipes(data);
                    mainView.setRefreshingProgressBar(false);
                }
            }

            @Override
            public void onError(Exception ex) {
                //TODO:convert ex to message via error factory
                if (mainView != null) {
                    mainView.showError(ex.getMessage());
                    mainView.setRefreshingProgressBar(false);
                }
            }
        });
    }

    private void loadCategories() {
        categoryRepository.getAll(new RepositoryDataLoadCallback<List<Category>>() {
            @Override
            public void onLoad(List<Category> data) {
                if (mainView != null) {
                    mainView.showCategories(data);
                    mainView.setRefreshingProgressBar(false);
                }
            }

            @Override
            public void onError(Exception ex) {
                //TODO:convert ex to message via error factory
                if (mainView != null) {
                    mainView.showError(ex.getMessage());
                    mainView.setRefreshingProgressBar(false);
                }
            }
        });
    }

    private void loadSlider() {
        sliderRepository.getAll(new RepositoryDataLoadCallback<List<Banner>>() {
            @Override
            public void onLoad(List<Banner> data) {
                if (mainView != null) {
                    mainView.showSlider(data);
                    mainView.setRefreshingProgressBar(false);
                }
            }

            @Override
            public void onError(Exception ex) {
                //TODO:convert ex to message via error factory
                if (mainView != null) {
                    mainView.showError(ex.getMessage());
                    mainView.setRefreshingProgressBar(false);
                }
            }
        });
    }

    @Override
    public void detachView() {
        this.mainView = null;
    }
}
