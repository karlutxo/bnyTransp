package eu.bonny.bnytransp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebActivity extends ActionBarActivity {

    private WebView myWebView;
    private ProgressBar mPbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webactivity_layout);
        myWebView = (WebView) findViewById(R.id.webView);

        myWebView.setWebViewClient(new WebViewClient(){
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.i("BNYGPS", "muestra pbar");
                mPbar.setVisibility(View.VISIBLE);
            }

            public void onPageFinished(WebView view, String url)
            {
                Log.i("BNYGPS", "oculta pbar");
                mPbar.setVisibility(View.GONE);
            }
        });

        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        mPbar = (ProgressBar) findViewById(R.id.progressBar);
//        myWebView.getSettings().setLoadWithOverviewMode(true);
//        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.loadUrl("http://lpa2.bonny.eu/frtd1500/WL4.transp");
    }


    @Override
    public void onBackPressed() {
        if(myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.webactivity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_map) {
            startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
