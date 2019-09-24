package android.example.mysevencook;

import android.example.mysevencook.main.MainFragment;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                //using replace() instead of add() because of rotations
                .replace(R.id.main_fl_container, new MainFragment())
                .commit();

    }
}
