package android.example.mysevencook.data.repo;

import android.example.mysevencook.data.Recipe;

import java.util.List;

public interface RecipeDataSource {
    public void getAll(RepositoryDataLoadCallback<List<Recipe>> callback);
}
