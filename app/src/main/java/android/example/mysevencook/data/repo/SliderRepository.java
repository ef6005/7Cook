package android.example.mysevencook.data.repo;

import android.example.mysevencook.data.Banner;

import java.util.List;

public class SliderRepository implements SliderDataSource {
    private SliderCloudDataSource sliderCloudDataSource;

    public SliderRepository(SliderCloudDataSource sliderCloudDataSource) {
        this.sliderCloudDataSource = sliderCloudDataSource;
    }

    @Override
    public void getAll(RepositoryDataLoadCallback<List<Banner>> repositoryDataLoadCallback) {
        sliderCloudDataSource.getAll(repositoryDataLoadCallback);
    }
}
