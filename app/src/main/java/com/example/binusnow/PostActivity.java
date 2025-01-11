package com.example.binusnow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PostActivity extends AppCompatActivity {

    private EditText postContentEditText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        DatabaseHelper dbHelper = new DatabaseHelper(this);

        postContentEditText = findViewById(R.id.post_content_edit_text);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(v -> {
            String postContent = postContentEditText.getText().toString();
            if (!postContent.isEmpty()) {
                dbHelper.insertPost(postContent); // Insert the post into the database
                Toast.makeText(PostActivity.this, "Post added successfully", Toast.LENGTH_SHORT).show();
                postContentEditText.setText("");
            }
        });

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