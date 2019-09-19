package android.example.mysevencook.data.repo;

import android.example.mysevencook.data.Category;

import java.util.List;

public class CategoryRepository implements CategoryDataSource {
    private CategoryCloudDataSource categoryCloudDataSource;

    public CategoryRepository(CategoryCloudDataSource categoryCloudDataSource) {
        this.categoryCloudDataSource = categoryCloudDataSource;
    }

    @Override
    public void getAll(RepositoryDataLoadCallback<List<Category>> repositoryDataLoadCallback) {
        categoryCloudDataSource.getAll(repositoryDataLoadCallback);
    }
}
