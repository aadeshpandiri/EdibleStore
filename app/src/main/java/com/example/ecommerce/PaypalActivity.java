package com.example.ecommerce;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PaypalActivity extends AppCompatActivity {

    private WebView webView;


    Activity activity ;
    private ProgressDialog progDailog;

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);

        activity = this;

        progDailog = ProgressDialog.show(activity, "Loading","Please wait...", true);
        progDailog.setCancelable(false);



        webView = (WebView) findViewById(R.id.webviewxml);



        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progDailog.show();
                view.loadUrl(url);

                return true;
            }
            @Override
            public void onPageFinished(WebView view, final String url) {
                progDailog.dismiss();
            }
        });
        //192.168.43.163
        webView.loadUrl("http://192.168.10.14:8080/?jwt=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmNAZ21haWwuY29tIiwiZXhwIjoxNjE2MjY4MTIxLCJpYXQiOjE2MTYyMzIxMjF9.bvkvwGNk4ygxYVrvtLlLIVgqa9siV4Ks7MTY2wMFNDk");

    }
}



