package com.example.binusnow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //    Bottom Navigation Template

        LinearLayout home = findViewById(R.id.HomeButton);
        LinearLayout post = findViewById(R.id.PostButton);
        LinearLayout profile = findViewById(R.id.ProfileButton);

        home.setOnClickListener(v -> {
            Intent intent = new Intent(AboutUsActivity.this, HomePageActivity.class);
            startActivity(intent);
        });

        post.setOnClickListener(v -> {
            Intent intent = new Intent(AboutUsActivity.this, PostActivity.class);
            startActivity(intent);
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(AboutUsActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

    }
}