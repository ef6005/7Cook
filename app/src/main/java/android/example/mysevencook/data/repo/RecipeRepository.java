package android.example.mysevencook.data.repo;

import android.example.mysevencook.data.Recipe;

import java.util.List;

public class RecipeRepository implements RecipeDataSource {
    private RecipeCloudDataSource recipeCloudDataSource;
    //private RecipeLocalDataSource

    public RecipeRepository(RecipeCloudDataSource recipeCloudDataSource) {
        this.recipeCloudDataSource = recipeCloudDataSource;
    }

    @Override
    public void getAll(RepositoryDataLoadCallback<List<Recipe>> callback) {
        //if(parameter)
        recipeCloudDataSource.getAll(callback);
        //else
        //recipeLocalDataSource.getAll(callback);
    }
}
