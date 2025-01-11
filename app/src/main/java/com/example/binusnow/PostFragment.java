package com.example.binusnow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class PostFragment extends Fragment {
    private static final String ARG_POST_ID = "post_id";
    private int postId;

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

        // Retrieve post content and replies from the database
        DatabaseHelper dbHelper = new DatabaseHelper(requireContext());
        String postContent = dbHelper.getPostContent(postId);
        ArrayList<String> replies = dbHelper.getRepliesForPost(postId);

        // Display post content
        TextView postContentTextView = view.findViewById(R.id.post_content);
        postContentTextView.setText(postContent);

        // Display replies
        ListView replyListView = view.findViewById(R.id.reply_list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                replies
        );
        replyListView.setAdapter(adapter);
        replyListView.setOnItemClickListener((parent, itemView, position, id) -> {
            String replyContent = replies.get(position);  // Get the content of the clicked reply
            ReplyFragment replyFragment = ReplyFragment.newInstance(replyContent);
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, replyFragment)
                    .addToBackStack(null)  // Enables navigating back to the previous fragment
                    .commit();
        });

        return view;
    }
}
