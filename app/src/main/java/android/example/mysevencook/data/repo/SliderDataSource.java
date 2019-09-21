package android.example.mysevencook.data.repo;

import android.example.mysevencook.data.Banner;

import java.util.List;

public interface SliderDataSource {
    void getAll(RepositoryDataLoadCallback<List<Banner>> repositoryDataLoadCallback);
}
