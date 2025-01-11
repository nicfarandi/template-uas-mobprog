package com.example.binusnow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class PostFragment extends Fragment {
    private static final String ARG_POST_ID = "post_id";
    private int postId;
    private EditText replyContentEditText;
    private Button submitReplyButton;
    private ListView replyListView;
    private ArrayAdapter<String> replyAdapter;
    private ArrayList<String> replies;

    public static PostFragment newInstance(int postId) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POST_ID, postId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            postId = getArguments().getInt(ARG_POST_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        DatabaseHelper dbHelper = new DatabaseHelper(requireContext());
        String postContent = dbHelper.getPostContent(postId);
        replies = dbHelper.getRepliesForPost(postId);

        TextView postContentTextView = view.findViewById(R.id.post_content);
        postContentTextView.setText(postContent);

        replyListView = view.findViewById(R.id.reply_list_view);
        replyAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, replies);
        replyListView.setAdapter(replyAdapter);
        replyListView.setOnItemClickListener((parent, itemView, position, id) -> {
            String replyContent = replies.get(position);  // Get the content of the clicked reply
            ReplyFragment replyFragment = ReplyFragment.newInstance(replyContent);
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, replyFragment)
                    .addToBackStack(null)  // Enables navigating back to the previous fragment
                    .commit();
        });

        // Add listener to insert reply
        replyContentEditText = view.findViewById(R.id.reply_content_edit_text);
        submitReplyButton = view.findViewById(R.id.submit_reply_button);

        submitReplyButton.setOnClickListener(v -> {
            String replyContent = replyContentEditText.getText().toString();
            if (!replyContent.isEmpty()) {
                insertReply(replyContent);
            } else {
                Toast.makeText(requireContext(), "Please write a reply", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void insertReply(String replyContent) {
        DatabaseHelper dbHelper = new DatabaseHelper(requireContext());
        dbHelper.insertReply(postId, replyContent);
        replies.add(replyContent);
        replyAdapter.notifyDataSetChanged();
        replyContentEditText.setText("");
        Toast.makeText(requireContext(), "Reply added successfully", Toast.LENGTH_SHORT).show();
    }
}

