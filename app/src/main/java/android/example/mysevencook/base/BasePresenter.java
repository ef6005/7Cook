package android.example.mysevencook.base;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
