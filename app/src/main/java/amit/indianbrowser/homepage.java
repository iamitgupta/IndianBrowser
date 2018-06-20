package amit.indianbrowser;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.net.URISyntaxException;
import java.util.logging.Logger;

public class homepage extends AppCompatActivity {
    WebView web;
    ProgressBar progressBar;
    private static long back_pressed;
    EditText et_link;
    Button bt_home, bt_option, bt_cancel;
    AdView Badview;
    private InterstitialAd interstitialAd;
    private ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener;
    public String mylink = null, googlesearch = "https://www.google.com/search?q=";
    private boolean mdesktop = false;
    private boolean mfacebooklite = false;
   public int a=0;
    Boolean fs=false;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        web = (WebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.pb);
        et_link = (EditText) findViewById(R.id.et_link);
        bt_option=(Button) findViewById(R.id.bt_option);
        linearLayout = (LinearLayout) findViewById(R.id.action_bar);

        MobileAds.initialize(this, getString(R.string.admob_app_id));

        //Banner adview
        Badview = (AdView) findViewById(R.id.BadView);
        AdRequest adRequest = new AdRequest.Builder().build();

        Badview.loadAd(adRequest);

        web.requestFocus();


        Intent i = getIntent();
        mylink = i.getStringExtra("mylink");
        //  Toast.makeText(getBaseContext(), "go the link as " +mylink, Toast.LENGTH_LONG).show();

        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //improve webView performance
        web.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        web.getSettings().setAppCacheEnabled(true);
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);




        if (mylink != null) {
            safetycheck(mylink);
            if ((mylink.startsWith("http")||(mylink.startsWith("https")) &&(mylink.endsWith(".com")) )) {
                web.setWebViewClient(new Browser());
                web.setWebChromeClient(new MyWebClient());
                web.getSettings().setJavaScriptEnabled(true);
                web.loadUrl(mylink);
            }
            else  if ((mylink.startsWith("www"))&&(mylink.endsWith(".com"))) {
                web.setWebViewClient(new Browser());
                web.setWebChromeClient(new MyWebClient());
                web.getSettings().setJavaScriptEnabled(true);
                web.loadUrl("https://" + mylink);
            }
            else  if (mylink.endsWith(".com")) {
                web.setWebViewClient(new Browser());
                web.setWebChromeClient(new MyWebClient());
                web.getSettings().setJavaScriptEnabled(true);
                web.loadUrl("http://www." + mylink);
            }

            else  {
                web.setWebViewClient(new Browser());
                web.setWebChromeClient(new MyWebClient());
                web.getSettings().setJavaScriptEnabled(true);
                web.loadUrl(googlesearch + mylink);
            }
        }


        bt_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupmenu();
            }
        });//closing the setOnClickListener method

       


        et_link.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    mylink = et_link.getText().toString();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_link.getWindowToken(), 0);
                    safetycheck(mylink);
                    if ((mylink.startsWith("http")||(mylink.startsWith("https")) &&(mylink.endsWith(".com")) )) {
                        web.setWebViewClient(new Browser());
                        web.setWebChromeClient(new MyWebClient());
                        web.getSettings().setJavaScriptEnabled(true);
                        web.loadUrl(mylink);
                    }
                    else  if ((mylink.startsWith("www"))&&(mylink.endsWith(".com"))) {
                        web.setWebViewClient(new Browser());
                        web.setWebChromeClient(new MyWebClient());
                        web.getSettings().setJavaScriptEnabled(true);
                        web.loadUrl("https://" + mylink);
                    }
                    else  if (mylink.endsWith(".com")) {
                        web.setWebViewClient(new Browser());
                        web.setWebChromeClient(new MyWebClient());
                        web.getSettings().setJavaScriptEnabled(true);
                        web.loadUrl("http://www." + mylink);
                    }

                    else  {
                        web.setWebViewClient(new Browser());
                        web.setWebChromeClient(new MyWebClient());
                        web.getSettings().setJavaScriptEnabled(true);
                        web.loadUrl(googlesearch + mylink);
                    }

                }
                return false;
            }


        });

        web.getSettings().setSupportZoom(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(false);

        web.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(progress);
                setTitle("Loading...");
                if (progress == 100) {
                    setTitle(view.getTitle());
                }
                super.onProgressChanged(view, progress);
            }
        });


        web.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

                request.setMimeType(mimeType);
                //------------------------COOKIE!!------------------------
                String cookies = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie", cookies);
                //------------------------COOKIE!!------------------------
                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription("Downloading file...");
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimeType));
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void popupmenu() {
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(homepage.this, bt_option);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.main_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent i=new Intent(getApplicationContext(),bookmarks.class);
                        startActivity(i);
                        finish();
                        return true;
                    case R.id.reload:
                        web.reload();
                        return true;
                    case R.id.forward:
                        if(web.canGoForward()){
                            web.goForward();
                        }
                        return true;

                    case R.id.share:
                     Intent  shareIntent=new Intent(Intent.ACTION_SEND);
                     shareIntent.setType("text/plain");
                     shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Shared via Tez Browser "+"\n"+web.getTitle());
                     shareIntent.putExtra(Intent.EXTRA_TEXT,web.getUrl());
                     startActivity(Intent.createChooser(shareIntent,"Share via"));
                        return true;
                    case R.id.desktop:

                        a++;
                        if(a%2!=0||a==1) {
                            mdesktop = false;
                            setDesktopMode(web, true);
                            Toast.makeText(getBaseContext(), "Desktop Mode enabled", Toast.LENGTH_LONG).show();
                        }
                        else{
                            mdesktop = true;
                            setDesktopMode(web, false);
                            Toast.makeText(getBaseContext(), "Desktop Mode disabled", Toast.LENGTH_LONG).show();
                        }
/*
                        if(item.isChecked()){
                            // If item already checked then unchecked it
                            item.setChecked(false);
                            mdesktop = false;
                            setDesktopMode(web, true);
                        }else{
                            // If item is unchecked then checked it
                            item.setChecked(true);
                            mdesktop = true;
                            setDesktopMode(web, false);
                        }
                       */
                        return true;
                    case R.id.facebooklite:
                        web.setWebViewClient(new Browser());
                        web.setWebChromeClient(new MyWebClient());
                        web.getSettings().setJavaScriptEnabled(true);
                        web.loadUrl("https.facebook.com");
                        a++;
                        if(a%2!=0||a==1) {
                            mdesktop = false;

                            setDesktopMode(web, true);
                            Toast.makeText(getBaseContext(), "Facebook Lite Mode enabled", Toast.LENGTH_LONG).show();
                        }
                        else{
                            mdesktop = true;
                            setDesktopMode(web, false);
                            Toast.makeText(getBaseContext(), "Facebook Lite mode disabled", Toast.LENGTH_LONG).show();
                        }
                        return true;

                    case R.id.downloadfile:
                      // Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));
                              return true;
                    case R.id.clearcache:
                        web.clearCache(true);
                        return true;
                    case R.id.cleardata:
                       cleardata();
                        return true;
                    case R.id.feedback:
                        Intent ifeed=new Intent(getApplicationContext(),feedback.class);
                        startActivity(ifeed);
                        return true;
                    case R.id.aboutapp:
                        Intent intent1=new Intent(getApplicationContext(),aboutapp.class);
                        startActivity(intent1);
                        return true;
                    case R.id.fullscreen:
                        if(fs==false){
                            fs=true;
                            linearLayout.setVisibility(View.GONE);
                        }
                        else{
                            fs=false;
                            linearLayout.setVisibility(View.VISIBLE);

                        }

                        return true;
                    case R.id.exit:
                        finish();
                        System.exit(0);
                        return true;
                }
                return true;
            }
        });

        popup.show();//showing popup menu
    }

    private void cleardata() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to clear All data ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                        web.clearCache(true);
                        web.clearHistory();
                        web.clearFormData();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


    class Browser extends WebViewClient {
        Browser() {
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getScheme().equals("market")) {
                try {
                    // startActivity(new Intent(Intent.ACTION_VIEW,
                    //  Uri.parse("market://details?id=" + getPackageName())));
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    Activity host = (Activity) view.getContext();
                    host.startActivity(intent);
                    return true;
                } catch (ActivityNotFoundException e) {
                    Uri uri = Uri.parse(url);
                    view.loadUrl("http://play.google.com/store/apps/" + uri.getHost() + "?" + uri.getQuery());
                    return false;
                }

            }
            else if (url.startsWith("intent://")) {
                try {
                    Context context = view.getContext();
                    Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);

                    if (intent != null) {
                        view.stopLoading();

                        PackageManager packageManager = context.getPackageManager();
                        ResolveInfo info = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
                        if (info != null) {
                            context.startActivity(intent);
                        } else {
                            String fallbackUrl = intent.getStringExtra("browser_fallback_url");
                            view.loadUrl(fallbackUrl);

                            // or call external broswer
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fallbackUrl));
//                    context.startActivity(browserIntent);
                        }

                        return true;
                    }
                } catch (URISyntaxException e) {

                }
            }

            else{
                progressBar.setVisibility(View.VISIBLE);
                view.loadUrl(url);
                safetycheck(web.getUrl());
                et_link.setText(web.getUrl());
                return true;

            }
            return true;
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
            safetycheck(web.getUrl());
            et_link.setText(web.getUrl());
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            safetycheck(web.getUrl());
            et_link.setText(web.getUrl());
            progressBar.setVisibility(View.GONE);
        }

    }

    public class MyWebClient extends WebChromeClient {
        private View mCustomView;
        private WebChromeClient.CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;



        public MyWebClient() {
        }

        public Bitmap getDefaultVideoPoster() {
            if (homepage.this == null) {
                return null;
            }
            return BitmapFactory.decodeResource(homepage.this.getApplicationContext().getResources(), 2130837573);
        }

        public void onHideCustomView() {
            ((FrameLayout) homepage.this.getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            homepage.this.getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            homepage.this.setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback) {
            if (this.mCustomView != null) {
                onHideCustomView();
                return;
            }
            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = homepage.this.getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = homepage.this.getRequestedOrientation();
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout) homepage.this.getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
            homepage.this.getWindow().getDecorView().setSystemUiVisibility(3846);
        }



    }

    @Override
    protected void onSaveInstanceState(Bundle outState )
    {
        super.onSaveInstanceState(outState);
        web.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        web.restoreState(savedInstanceState);
    }

    public void safetycheck(String mylink){
        if (mylink.startsWith("https")){
            et_link.setCompoundDrawablesWithIntrinsicBounds( R.drawable.secure, 0, 0, 0);
        }
        else {
            et_link.setCompoundDrawablesWithIntrinsicBounds( R.drawable.insecure, 0, 0, 0);
        }

    }
    public void setDesktopMode(WebView webView,boolean enabled) {
        String newUserAgent = webView.getSettings().getUserAgentString();
        if (enabled) {
            try {
                String ua = webView.getSettings().getUserAgentString();
                String androidOSString = webView.getSettings().getUserAgentString().substring(ua.indexOf("("), ua.indexOf(")") + 1);
                newUserAgent = webView.getSettings().getUserAgentString().replace(androidOSString, "(X11; Linux x86_64)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            newUserAgent = null;
        }

        webView.getSettings().setUserAgentString(newUserAgent);
        webView.getSettings().setUseWideViewPort(enabled);
        webView.getSettings().setLoadWithOverviewMode(enabled);
        webView.reload();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        switch (keyCode){
            case KeyEvent.KEYCODE_MENU:
                popupmenu();
                return true;
            case KeyEvent.KEYCODE_BACK:
                if(web.canGoBack()){
                    web.goBack();
                    return true;
                }
                else if (!web.canGoBack()) {
                    Intent i = new Intent(getApplicationContext(), bookmarks.class);
                    startActivity(i);
                    finish();
                }

        }
        return super.onKeyDown(keyCode, event);
    }

}