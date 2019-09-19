package android.example.mysevencook.data.repo;

import android.example.mysevencook.data.Category;

import java.util.List;

public interface CategoryDataSource {
    void getAll(RepositoryDataLoadCallback<List<Category>> repositoryDataLoadCallback);
}
