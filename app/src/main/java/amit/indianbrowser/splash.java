package amit.indianbrowser;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class splash extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    private Handler handler = new Handler();
    AdView Badview;
    private InterstitialAd interstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int time=3000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.pb);
        textView = (TextView) findViewById(R.id.textView);
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
                System.exit(0);

            }
        });

        if(connection()) {
                new Handler().postDelayed(new Runnable() {
                @Override
                 public void run() {
            new Thread(new Runnable() {
                public void run() {
                    while (progressStatus < 100) {
                        progressStatus += 1;
                        //Update progress bar with completion of operation
                        handler.post(new Runnable() {
                            public void run() {
                                progressBar.setProgress(progressStatus);
                                textView.setText("Loading: " + progressStatus +" %");
                                textView.setTextColor(getResources().getColor(R.color.green));
                            }
                        });
                        try {
                            // Sleep for 200 milliseconds.
                            //Just to display the progress slowly
                            Thread.sleep(15);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            Intent i = new Intent(getApplicationContext(), bookmarks.class);
            startActivity(i);
            finish();

        }
          },time);
        }
        else{
            new Thread(new Runnable() {
                public void run() {
                    while (progressStatus < 20) {
                        progressStatus += 1;
                        //Update progress bar with completion of operation
                        handler.post(new Runnable() {
                            public void run() {
                                progressBar.setProgress(progressStatus);
                                textView.setText("Loading: "+progressStatus+" %");
                                textView.setText("No internet Conncection ");

                            }
                        });
                        try {
                            // Sleep for 200 milliseconds.
                            //Just to display the progress slowly
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            textView.setTextColor(getResources().getColor(R.color.red));
            Toast.makeText(getBaseContext(), "Check your Internect Connection !!", Toast.LENGTH_LONG).show();
           // System.exit(0);
        }



        //Long operation by thread

    }
    public boolean connection(){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            //connected = true;
            return true;
        }
        else
            //connected = false;
        return false;

    }
    @Override
    public void onBackPressed() {
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            System.exit(0);
        }

    }
}
