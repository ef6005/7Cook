package android.example.mysevencook.data.repo;

import android.example.mysevencook.data.Slider;

import java.util.List;

public class SliderRepository implements SliderDataSource {
    private SliderCloudDataSource sliderCloudDataSource;

    public SliderRepository(SliderCloudDataSource sliderCloudDataSource) {
        this.sliderCloudDataSource = sliderCloudDataSource;
    }

    @Override
    public void getAll(RepositoryDataLoadCallback<List<Slider>> repositoryDataLoadCallback) {
        sliderCloudDataSource.getAll(repositoryDataLoadCallback);
    }
}
