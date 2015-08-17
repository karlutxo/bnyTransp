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
import android.widget.Toast;


// TODO: probar injection código para saltar ffww
//http://www.techrepublic.com/article/pro-tip-inject-javascript-into-an-android-web-view-for-a-more-dynamic-ux/

public class WebActivity extends ActionBarActivity {

    private WebView myWebView;
    private ProgressBar mPbar = null;
    private String server1 = "http://l**6.b****.eu:8888/t/wl4.transp";
    private String server2 = "http://l**2.b****.eu/frtd1500/WL4.transp";


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

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), "Fallo servidor principal, conectando serv alternativo", Toast.LENGTH_SHORT).show();
                myWebView.loadUrl(server2);

            }
        });

        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        mPbar = (ProgressBar) findViewById(R.id.progressBar);
//        myWebView.getSettings().setLoadWithOverviewMode(true);
//        myWebView.getSettings().setUseWideViewPort(true);
        if (savedInstanceState == null) {
            myWebView.loadUrl(server1);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState )
    {
        super.onSaveInstanceState(outState);
        myWebView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        myWebView.restoreState(savedInstanceState);
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
