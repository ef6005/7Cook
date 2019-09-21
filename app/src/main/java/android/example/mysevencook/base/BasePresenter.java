package android.example.mysevencook.base;

public interface BasePresenter<T extends BaseView> {
    void onAttach(T view);

    void onDetach();
}
