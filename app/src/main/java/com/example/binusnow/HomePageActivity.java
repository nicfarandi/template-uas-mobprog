package com.example.binusnow;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Take Post data from database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        // populate, comment after run once
//        dbHelper.insertPost("Hello, this is my first post!");
//        dbHelper.insertPost("Learning SQLite in Android is fun!");
//        dbHelper.insertPost("This is another post example.");
//        dbHelper.insertReply(1, "First reply to post 1");
//        dbHelper.insertReply(1, "Second reply to post 1");
//        dbHelper.insertReply(2, "Reply to post 2");
        ListView listView = findViewById(R.id.listView);
        ArrayList<String> stringList = dbHelper.getAllPostContent();

        // Create an ArrayAdapter using the stringList and a default layout
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedPost = stringList.get(position);
                Intent intent = new Intent(HomePageActivity.this, PostDetailActivity.class);
                intent.putExtra("post_id", position + 1);
                startActivity(intent);
            }
        });

        // Bottom Navigation Template
        LinearLayout post = findViewById(R.id.PostButton);
        LinearLayout profile = findViewById(R.id.ProfileButton);
        LinearLayout about = findViewById(R.id.AboutButton);

        post.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, PostActivity.class);
            startActivity(intent);
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        about.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, AboutUsActivity.class);
            startActivity(intent);
        });
    }

}