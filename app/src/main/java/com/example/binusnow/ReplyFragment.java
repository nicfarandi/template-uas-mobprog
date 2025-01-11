package com.example.binusnow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class ReplyFragment extends Fragment {
    private static final String ARG_REPLY_CONTENT = "reply_content";

    public static ReplyFragment newInstance(String replyContent) {
        ReplyFragment fragment = new ReplyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_REPLY_CONTENT, replyContent);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reply, container, false);
        TextView replyContentTextView = view.findViewById(R.id.reply_content);

        if (getArguments() != null) {
            String replyContent = getArguments().getString(ARG_REPLY_CONTENT);
            replyContentTextView.setText(replyContent);
        }
        return view;
    }
}
