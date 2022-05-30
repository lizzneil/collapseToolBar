package com.gabe.collapsetoolbar.share;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.gabe.collapsetoolbar.R;

public class ShareToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_toolbar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.share_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.share_toolbar_menu, menu);

        //设置各项的图标。
        for(int i=0;i <menu.size();i++ ){
            MenuItem it  = menu.getItem(i);
            it.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_scan_qr_24dp));
            it.getIcon().setTint(Color.BLUE);
        }
        initShare(menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void initShare(Menu menu){
        MenuItem shareItem = menu.findItem(R.id.action_share_right);
        ShareActionProvider myShareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);


//        Create an intent with the Intent.ACTION_SEND action,
//        and attach the content shared by the activity. For example, the following intent shares an image:

        Intent myShareIntent = new Intent(Intent.ACTION_SEND);
        myShareIntent.setType("image/*");
        myShareIntent.putExtra(Intent.EXTRA_STREAM, (Bundle) null);//myShareIntent.putExtra(Intent.EXTRA_STREAM, myImageUri);
//        Call setShareIntent() to attach this intent to the action provider:

        myShareActionProvider.setShareIntent(myShareIntent);

////        When the content changes, modify the intent or create a new one,
////        and call setShareIntent() again. For example:
//
//        // Image has changed! Update the intent:
//        myShareIntent.putExtra(Intent.EXTRA_STREAM, (Bundle) null);//myShareIntent.putExtra(Intent.EXTRA_STREAM, myNewImageUri);
//        myShareActionProvider.setShareIntent(myShareIntent);
    }
}