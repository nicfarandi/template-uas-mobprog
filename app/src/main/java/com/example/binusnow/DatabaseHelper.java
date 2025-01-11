package com.example.binusnow;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database constants
    private static final String DATABASE_NAME = "PostReplyDB.db";
    private static final int DATABASE_VERSION = 1;

    // Table creation queries
    private static final String CREATE_POST_TABLE = "CREATE TABLE Post (" +
            "post_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "content TEXT NOT NULL);";

    private static final String CREATE_REPLY_TABLE = "CREATE TABLE Reply (" +
            "reply_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "post_id INTEGER NOT NULL, " +
            "content TEXT NOT NULL, " +
            "FOREIGN KEY(post_id) REFERENCES Post(post_id) ON DELETE CASCADE);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_POST_TABLE);
        db.execSQL(CREATE_REPLY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Reply");
        db.execSQL("DROP TABLE IF EXISTS Post");
        onCreate(db);
    }

    public ArrayList<String> getAllPostContent() {
        ArrayList<String> postContentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT content FROM Post";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String content = cursor.getString(0);  // Index 0 corresponds to the content column
                postContentList.add(content);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return postContentList;
    }

    public void insertPost(String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO Post (content) VALUES (?)";
        db.execSQL(query, new Object[]{content});
        db.close();
    }

    public void insertReply(int postId, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO Reply (post_id, content) VALUES (?, ?)";
        db.execSQL(query, new Object[]{postId, content});
        db.close();
    }

    public String getPostContent(int postId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT content FROM Post WHERE post_id = ?", new String[]{String.valueOf(postId)});
        String content = cursor.moveToFirst() ? cursor.getString(0) : "Post not found.";
        cursor.close();
        db.close();
        return content;
    }

    public ArrayList<String> getRepliesForPost(int postId) {
        ArrayList<String> replyList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT content FROM Reply WHERE post_id = ?", new String[]{String.valueOf(postId)});
        if (cursor.moveToFirst()) {
            do {
                replyList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return replyList;
    }

}
