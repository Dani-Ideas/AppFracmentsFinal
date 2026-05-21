package com.example.appfracmentsfinal;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);

        String[] elements = {
                getString(R.string.cat),
                getString(R.string.dog),
                getString(R.string.cow)
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                elements
        );

        listView.setAdapter(adapter);

        if (savedInstanceState == null) {
            replaceFragment(new CatFragment());
        }

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Fragment selectedFragment;
            switch (position) {
                case 0:
                    selectedFragment = new CatFragment();
                    break;
                case 1:
                    selectedFragment = new DogFragment();
                    break;
                case 2:
                    selectedFragment = new CowFragment();
                    break;
                default:
                    selectedFragment = new CatFragment();
            }
            replaceFragment(selectedFragment);
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}