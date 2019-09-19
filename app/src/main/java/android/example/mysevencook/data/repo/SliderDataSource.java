package android.example.mysevencook.data.repo;

import android.example.mysevencook.data.Slider;

import java.util.List;

public interface SliderDataSource {
    void getAll(RepositoryDataLoadCallback<List<Slider>> repositoryDataLoadCallback);
}
