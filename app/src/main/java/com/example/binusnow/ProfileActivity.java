package com.example.binusnow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //    Bottom Navigation Template

        LinearLayout home = findViewById(R.id.HomeButton);
        LinearLayout post = findViewById(R.id.PostButton);
        LinearLayout about = findViewById(R.id.AboutButton);

        home.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, HomePageActivity.class);
            startActivity(intent);
        });

        post.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, PostActivity.class);
            startActivity(intent);
        });

        about.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, AboutUsActivity.class);
            startActivity(intent);
        });
    }
}