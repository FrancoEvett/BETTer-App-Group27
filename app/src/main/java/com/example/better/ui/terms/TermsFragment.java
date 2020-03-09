package com.example.better.ui.terms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.better.R;

public class TermsFragment extends Fragment {

    private TermsViewModel termsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        termsViewModel =
                ViewModelProviders.of(this).get(TermsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_terms, container, false);
        final TextView textView = root.findViewById(R.id.text_terms);
        termsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}