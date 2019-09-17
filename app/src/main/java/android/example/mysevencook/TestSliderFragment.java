package android.example.mysevencook;

import android.example.mysevencook.adapter.SliderAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TestSliderFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.test_fragment_slider, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ApiService.getInstance(getContext()).loadSliders(new ApiServiceCallBack<List<Slider>>() {
            @Override
            public void onLoad(List<Slider> data) {
                RecyclerView bannersRv = getView().findViewById(R.id.test_slider_rv_banners);
                SliderAdapter bannerAdapter = new SliderAdapter(data);
                bannersRv.setAdapter(bannerAdapter);
                bannersRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            }

            @Override
            public void onError(Exception ex) {
                Toast.makeText(getContext(), "ERROR Loading Banners", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
