package amit.indianbrowser;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
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
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import static android.view.View.FOCUS_LEFT;
import static android.view.View.FOCUS_RIGHT;


public class bookmarks extends AppCompatActivity {

    ProgressBar progressBar;
    private static long back_pressed;
    EditText et_link;
    Button  bt_option,bt_fb,bt_insta,bt_twitter,bt_google,bt_gmail,bt_youtube,bt_flipkart,bt_amazon,bt_myntra,bt_paytm,bt_mp3,bt_shopclues;
    String mylink;
    AdView Badview;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        progressBar = (ProgressBar) findViewById(R.id.pb);
        bt_option=(Button) findViewById(R.id.bt_option);
        bt_fb=(Button) findViewById(R.id.bt_fb);
        bt_insta=(Button) findViewById(R.id.bt_insta);
        bt_twitter=(Button) findViewById(R.id.bt_twitter);
        bt_google=(Button) findViewById(R.id.bt_google);
        bt_gmail=(Button) findViewById(R.id.bt_gmail);
        bt_youtube=(Button) findViewById(R.id.bt_youtube);
        bt_flipkart=(Button) findViewById(R.id.bt_flipkart);
        bt_amazon=(Button) findViewById(R.id.bt_amazon);
        bt_myntra=(Button) findViewById(R.id.bt_myntra);

        bt_shopclues=(Button) findViewById(R.id.bt_shopclues);
        bt_paytm=(Button) findViewById(R.id.bt_paytm);
        bt_mp3=(Button) findViewById(R.id.bt_mp3);



        MobileAds.initialize(this, getString(R.string.admob_app_id));

        //Banner adview
        Badview = (AdView) findViewById(R.id.BadView);
        AdRequest adRequest = new AdRequest.Builder().build();

        Badview.loadAd(adRequest);

        //Interstial ads
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        interstitialAd.loadAd(new AdRequest.Builder().build());

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                interstitialAd.loadAd(new AdRequest.Builder().build());
                finish();
                System.exit(0);

            }
        });


bt_google.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(getApplicationContext(),homepage.class);
        i.putExtra("mylink","https://www.google.com");
        startActivity(i);
        finish();

    }
});
        bt_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),homepage.class);
                i.putExtra("mylink","https://m.facebook.com");
               // Toast.makeText(getBaseContext(), "Welcome " +mylink, Toast.LENGTH_LONG).show();
                startActivity(i);
                finish();

            }
        });
        bt_insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),homepage.class);
                i.putExtra("mylink","https://www.instagram.com");
                startActivity(i);
                finish();

            }
        });
        bt_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),homepage.class);
                i.putExtra("mylink","https://mobile.twitter.com");
                startActivity(i);
                finish();

            }
        });
        bt_gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),homepage.class);
                i.putExtra("mylink","https://www.gmail.com");
                startActivity(i);
                finish();

            }
        });
        bt_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),homepage.class);
                i.putExtra("mylink","https://youtube.com");
                startActivity(i);
                finish();

            }
        });
        bt_flipkart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),homepage.class);
                i.putExtra("mylink","https://www.flipkart.com");
                startActivity(i);
                finish();

            }
        });
        bt_amazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),homepage.class);
                i.putExtra("mylink","https://www.amazon.in");
                startActivity(i);
                finish();

            }
        });
        bt_myntra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),homepage.class);
                i.putExtra("mylink","https://myntra.com");
                startActivity(i);
                finish();

            }
        });
        bt_shopclues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),homepage.class);
                i.putExtra("mylink","https://m.shopclues.com");
                startActivity(i);
                finish();

            }
        });

        bt_paytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),homepage.class);
                i.putExtra("mylink","https://paytm.com");
                startActivity(i);
                finish();

            }
        });
        bt_mp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),homepage.class);
                i.putExtra("mylink","https://gaana.com/");
                startActivity(i);
                finish();

            }
        });





        et_link = (EditText) findViewById(R.id.et_link);
        et_link.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    mylink = et_link.getText().toString();
                   // hideSoftInput(v);

                    InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_link.getWindowToken(),0);
                    Intent i=new Intent(bookmarks.this,homepage.class);
                            i.putExtra("mylink",mylink);

                    startActivity(i);
                    finish();

                }
                return false;
            }
        });




    }
    public void hideSoftInput(View view){
        InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);

    }
/*    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            web.goBack();
            return true;
        }
        else if(keyCode == KeyEvent.KEYCODE_){
            Intent i=new Intent(getApplicationContext(),bookmarks.class);
            startActivity(i);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
/*    /*
@Override
public void onBackPressed() {


    if (interstitialAd.isLoaded()) {
        interstitialAd.show();
    }
    else {
        finish();
        System.exit(0);
        }

}
*/
    private long backPressedTime = 0;
    @Override
    public void onBackPressed() {        // to prevent irritating accidental logouts
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {    // 2 secs
            backPressedTime = t;
            Toast.makeText(this, "Press Back again to Exit",
                    Toast.LENGTH_SHORT).show();
        } else {    // this guy is serious
            // clean up
            if (interstitialAd.isLoaded()) {
                interstitialAd.show();
            }
            else {
                finish();
                System.exit(0);
            }
           // super.onBackPressed();       // bye
        }
    }
}

