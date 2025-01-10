package com.example.binusnow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReplyFragment extends Fragment {
    private static final String ARG_REPLY = "reply";

    public static ReplyFragment newInstance(String replyText) {
        ReplyFragment fragment = new ReplyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_REPLY, replyText);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reply, container, false);
        TextView replyTextView = view.findViewById(R.id.replyTextView);
        String replyText = getArguments().getString(ARG_REPLY);
        replyTextView.setText(replyText);
        return view;
    }
}
