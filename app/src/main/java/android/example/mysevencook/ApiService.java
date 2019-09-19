package android.example.mysevencook;

import android.content.Context;
import android.example.mysevencook.data.Category;
import android.example.mysevencook.data.Recipe;
import android.example.mysevencook.data.Slider;
import android.example.mysevencook.data.repo.RepositoryDataLoadCallback;
import android.example.mysevencook.service.http.GsonRequest;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ApiService {
    private static final String RECIPES_URL = "https://api.myjson.com/bins/n7bxs";
    private static final String SLIDERS_URL = "https://api.myjson.com/bins/110sw0";
    private static final String CATEGORIES_URL = "https://api.myjson.com/bins/v0bog";
    private static RequestQueue requestQueue;

    private ApiService(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
    }

    public static ApiService getInstance(Context context) {
        return new ApiService(context);
    }

    public void loadRecipes(RepositoryDataLoadCallback<List<Recipe>> callBack) {
        GsonRequest<List<Recipe>> recipesRequest = new GsonRequest<>(Request.Method.GET, RECIPES_URL
                , volleyError -> callBack.onError(volleyError)
                , recipes -> callBack.onLoad(recipes)
                , new TypeToken<List<Recipe>>() {
        }.getType());

        requestQueue.add(recipesRequest);
    }

    public void loadSliders(RepositoryDataLoadCallback<List<Slider>> callBack) {
        GsonRequest<List<Slider>> slidersRequest = new GsonRequest<>(Request.Method.GET, SLIDERS_URL
                , volleyError -> callBack.onError(volleyError)
                , sliders -> callBack.onLoad(sliders)
                , new TypeToken<List<Slider>>() {
        }.getType());
        requestQueue.add(slidersRequest);
    }

    public void loadCategories(RepositoryDataLoadCallback<List<Category>> callBack) {
        GsonRequest<List<Category>> categoriesRequest = new GsonRequest<>(Request.Method.GET, CATEGORIES_URL
                , volleyError -> callBack.onError(volleyError)
                , categories -> callBack.onLoad(categories)
                , new TypeToken<List<Category>>() {
        }.getType());

        requestQueue.add(categoriesRequest);
    }
}
