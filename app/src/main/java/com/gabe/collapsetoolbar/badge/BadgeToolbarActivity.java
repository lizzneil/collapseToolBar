package com.gabe.collapsetoolbar.badge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gabe.collapsetoolbar.R;

public class BadgeToolbarActivity extends AppCompatActivity {

    private static final String TAG = BadgeToolbarActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge_toolbar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.badge_toolbar);
        setSupportActionBar(myToolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.badge_toolbar_menu, menu);

        //设置各项的图标。
        for(int i=0;i <menu.size();i++ ){
            MenuItem it  = menu.getItem(i);
            it.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_scan_qr_24dp));
            it.getIcon().setTint(Color.BLUE);
        }

        initBadge();

        return super.onCreateOptionsMenu(menu);
    }

    private BadgeActionProvider mBadgeActionProvider;
    int badgeCnt = 0;
    private void initBadge() {
        Toolbar mToolBar = (Toolbar) findViewById(R.id.badge_toolbar);

        MenuItem menuItem = mToolBar.getMenu().findItem(R.id.action_badge_refresh);
        mBadgeActionProvider = (BadgeActionProvider) MenuItemCompat.getActionProvider(menuItem);
        mBadgeActionProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badgeCnt++;
                mBadgeActionProvider.setBadge(badgeCnt);
                Toast.makeText(BadgeToolbarActivity.this,"来一下 \t now :"+badgeCnt,Toast.LENGTH_SHORT).show();

            }
        });
    }


}