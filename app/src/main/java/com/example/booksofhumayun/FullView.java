package com.example.booksofhumayun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.xw.repo.BubbleSeekBar;


public class FullView extends AppCompatActivity {

    WebView webView;
    String[] chapters;

    CardView previousButton, nextButton;

    RelativeLayout relativeLayout;
    LinearLayout bottomSheetLayout;
    BottomSheetBehavior bottomSheetBehavior;


    BubbleSeekBar bubbleSeekBar;
    Switch darkThemeSwitch;

    int part;
    int total_chapters;

    CoordinatorLayout coordinatorLayout;

    ImageView downButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        chapters = getResources().getStringArray(R.array.chapter_serial);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        darkThemeSwitch = findViewById(R.id.darkThemeSwitch);
        downButton = findViewById(R.id.downButton);

        bottomSheetLayout = findViewById(R.id.bottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout);

        previousButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);

        webView = findViewById(R.id.webView);





        final String file = getIntent().getStringExtra("book_name_english");
        final String title = getIntent().getStringExtra("title");
        String total_chapter_string = getIntent().getStringExtra("total_chapters");
        total_chapters = Integer.parseInt(total_chapter_string);




        String currentChapter = getIntent().getStringExtra("currentChapter");
        part = Integer.parseInt(currentChapter);



        getSupportActionBar().setTitle(title+" - পর্ব "+ chapters[part-1]);
        relativeLayout = findViewById(R.id.relative);


        final String folderName = getIntent().getStringExtra("folderName");


        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new myWebClientClass());
        webView.loadUrl("file:///android_asset/" + folderName + "/" + file + ".html");







        darkThemeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked)
                {
                    coordinatorLayout.setBackgroundColor(getColor(R.color.dark));
                    webView.setBackgroundColor(getColor(R.color.dark));
                    webView.loadUrl("javascript:document.body.style.setProperty(\"color\", \"white\");");
                }

                else
                {
                    coordinatorLayout.setBackgroundColor(getColor(R.color.white));
                    webView.setBackgroundColor(getColor(R.color.white));
                    webView.loadUrl("javascript:document.body.style.setProperty(\"color\", \"black\");");
                }

            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(part<total_chapters)
                {
                    part++;

                    webView.loadUrl("file:///android_asset/" + folderName + "/" + folderName+part +".html");
                    getSupportActionBar().setTitle(title + " - পর্ব " +chapters[part-1]);
                }


            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(part>1)
                {
                    part--;

                    webView.loadUrl("file:///android_asset/" + folderName + "/" + folderName+part +".html");
                    getSupportActionBar().setTitle(title + " - পর্ব " +chapters[part-1]);
                }
            }
        });





        bubbleSeekBar = findViewById(R.id.textSizeSeekbar);
        bubbleSeekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
                int x = (int) (bubbleSeekBar.getProgress());
                WebSettings webSettings = webView.getSettings();
                webSettings.setDefaultFontSize(x);
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
            }
        });






        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED)
                {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                else
                {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });
    }






    public class myWebClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            if (darkThemeSwitch.isChecked())
            {
                coordinatorLayout.setBackgroundColor(getColor(R.color.dark));
                webView.setBackgroundColor(getColor(R.color.dark));
                webView.loadUrl("javascript:document.body.style.setProperty(\"color\", \"white\");");
            }

            relativeLayout.setVisibility(View.GONE);
            view.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            if (darkThemeSwitch.isChecked())
            {
                coordinatorLayout.setBackgroundColor(getColor(R.color.dark));
                webView.setBackgroundColor(getColor(R.color.dark));
                webView.loadUrl("javascript:document.body.style.setProperty(\"color\", \"white\");");
            }
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            relativeLayout.setVisibility(View.VISIBLE);
            view.setVisibility(View.GONE);
        }
    }












    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
                this.finish();
                break;

            case R.id.settingsMenuId:
                if(bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED)
                {
                    bottomSheetBehavior.setState(bottomSheetBehavior.STATE_EXPANDED);
                    item.setIcon(R.drawable.ic_close);
                }
                else
                {
                    bottomSheetBehavior.setState(bottomSheetBehavior.STATE_COLLAPSED);
                    item.setIcon(R.drawable.ic_menu2);
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
