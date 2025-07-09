package com.bignerdranch.android.webview;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.OnBackPressedCallback;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        webView = (WebView) findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient()); //prevents opening in browser

        WebSettings webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true); // For modern HTML5 storage
        webSettings.setDatabaseEnabled(true);   // Optional, for legacy localStorage
        webSettings.setJavaScriptEnabled(true); // Only if site needs JS

// Enable smart caching behavior
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT); // Uses cache if valid


        webView.loadUrl("https://ais.ajou.uz/en");

        this.getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish(); // or requireActivity().finish() in a fragment
                }
            }
        });

    }



}