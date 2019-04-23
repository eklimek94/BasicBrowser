package peppersapplications.com.basicbrowser;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;
import android.view.ContextMenu;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import peppersapplications.com.basicbrowser.R;

public class MainActivity extends AppCompatActivity {

    private MenuItem[] bookmarkedURLS = new MenuItem[7];
    private int menuItemTracker=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView wv = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = wv.getSettings();
        webSettings.setBuiltInZoomControls(true);
        wv.loadUrl(
                "http://chart.apis.google.com/chart" +
                        "?chs=300x225" +
                        "&cht=v" +
                        "&chco=FF6342,ADDE63,63C6DE" +
                        "&chd=t:100,80,60,30,30,30,10" +
                        "&chdl=A|B|C");
    }


    //takes edit text URL and displays it in the provided webview.
    private void websiteLoader(String webUrl,WebView webview){
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(webUrl);
    }

    //code for clicking Submit
    public void onClickSubmit(View view) {
        EditText txtUrl = (EditText) findViewById(R.id.txtUrl);
        WebView wv = (WebView) findViewById(R.id.webview);
        String webUrl;

        webUrl = txtUrl.getText().toString();

        websiteLoader(webUrl,wv);
    }


    public void onClickBookmark(View view){
        EditText txtUrl = (EditText) findViewById(R.id.txtUrl);
        String webUrl;
        webUrl = txtUrl.getText().toString();

        bookmarking(webUrl);
        Toast.makeText(this, "Bookmarked",
                Toast.LENGTH_LONG).show();
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, view, menuInfo);
        createMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        createMenu(menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return menuChoice(item);
    }
    private void createMenu(Menu menu) {
        MenuItem[] bookmarkedURLS = new MenuItem[7];
        bookmarkedURLS[0] = menu.add(0, 0, 0, "blank");
        bookmarkedURLS[1] = menu.add(0, 1, 1, "blank");
        bookmarkedURLS[2] = menu.add(0, 2, 2, "blank");
        bookmarkedURLS[3] = menu.add(0, 3, 3, "blank");
        bookmarkedURLS[4] = menu.add(0, 4, 4, "blank");
        bookmarkedURLS[5] = menu.add(0, 5, 5, "blank");
        bookmarkedURLS[6] = menu.add(0, 6, 6, "blank");

        this.bookmarkedURLS=bookmarkedURLS;


    }
    private boolean menuChoice(MenuItem item) {
        WebView wv = (WebView) findViewById(R.id.webview);
        websiteLoader(item.getTitle().toString(),wv);

                return true;

    }

    //assigns the value of the editext URL to the menu item title
    public void bookmarking(String webUrl){
        bookmarkedURLS[menuItemTracker].setTitle(webUrl);
        menuItemTracker++;

        if(menuItemTracker>6){
            menuItemTracker=0;
        }
    }
}