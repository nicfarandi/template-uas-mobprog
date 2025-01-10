package com.example.binusnow;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PostDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        String postText = getIntent().getStringExtra("post");
        PostFragment postFragment = PostFragment.newInstance(postText);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, postFragment)
                .commit();
    }

}