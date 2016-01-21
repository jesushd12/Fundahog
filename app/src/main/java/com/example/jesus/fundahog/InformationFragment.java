package com.example.jesus.fundahog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by Jesus on 1/16/2016.
 */
public class InformationFragment extends Fragment {
    WebView webView;
    final private String url = "http://www.cancer.gov/espanol/cancer/que-es";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_information,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Informacion");
        webView = (WebView)v.findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);

        return v;
    }
}
