package com.example.binusnow;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> postsList = new ArrayList<>();
    private DatabaseHelper dbHelper;

    private void insertSampleData() {
        dbHelper.insertPost("What is your favorite programming language?", "My favorite is Python.");
        dbHelper.insertPost("How do you manage time effectively?", "I use a task manager and set priorities.");
        dbHelper.insertPost("Tips for learning Android development?", "Start with the official documentation and build projects.");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        listView = findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);

        loadPosts();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, postsList);
        listView.setAdapter(adapter);

        //    Bottom Navigation Template

        LinearLayout post = findViewById(R.id.PostButton);
        LinearLayout profile = findViewById(R.id.ProfileButton);
        LinearLayout about = findViewById(R.id.AboutButton);

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        about.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, AboutUsActivity.class);
            startActivity(intent);
        });
    }

    private void loadPosts() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, new String[]{DatabaseHelper.COLUMN_POST}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String post = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_POST));
            postsList.add(post);
        }
        cursor.close();
    }
}