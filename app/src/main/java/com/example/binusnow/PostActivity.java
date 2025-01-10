package com.example.binusnow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_post);
        //    Bottom Navigation Template

        LinearLayout home = findViewById(R.id.HomeButton);
        LinearLayout profile = findViewById(R.id.ProfileButton);
        LinearLayout about = findViewById(R.id.AboutButton);

        home.setOnClickListener(v -> {
            Intent intent = new Intent(PostActivity.this, HomePageActivity.class);
            startActivity(intent);
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(PostActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        about.setOnClickListener(v -> {
            Intent intent = new Intent(PostActivity.this, AboutUsActivity.class);
            startActivity(intent);
        });
    }
}