package hr.dbab.mynews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        WebView webView = findViewById(R.id.webview);

        final String url = getIntent().getStringExtra("url");

        webView.loadUrl(url);
    }
}
