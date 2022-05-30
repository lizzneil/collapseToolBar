package com.gabe.collapsetoolbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.gabe.collapsetoolbar.animation.AnimationToolbarActivity;
import com.gabe.collapsetoolbar.badge.BadgeToolbarActivity;
import com.gabe.collapsetoolbar.lottie.LottieToolBarActivity;
import com.gabe.collapsetoolbar.search.SearchToolBarActivity;
import com.gabe.collapsetoolbar.share.ShareToolbarActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        WindowCompat.setDecorFitsSystemWindows(getWindow(),false);

//1. Hide ActionBar from the entire App using styles.xml
//    <!---Base application theme. -->
//    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">

//2. Hide ActionBar from any particular activity using Java code
//有效 隐藏自带的toolbar (即使主题里定义了，依然有效)
        // Take instance of Action Bar
        // using getSupportActionBar and
        // if it is not Null
        // then call hide function
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


//3. Hide ActionBar while user interaction using WindowManager
// 主题里定义了，这里无效。
        // set Windows Flags to Full Screen
        // using setFlags function
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//4. Hide ActionBar while user interaction using requestWindowFeature
        // 主题里定义了，这里无效。
//    requestWindowFeature(Window.FEATURE_NO_TITLE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //状态栏 底色变化。
            window.setStatusBarColor(Color.YELLOW);
            //状态栏 图示变暗。 电池 信号等图标
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //状态栏 底色变化。
            window.setStatusBarColor(Color.TRANSPARENT);
            //状态栏 图示变暗。 电池 信号等图标
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_main);
    }

    public void seeToolBar(View aView){
        Intent toActivity = new Intent(this, ExperimentToolbarActivity.class);
        this.startActivity(toActivity);
    }

    public void seeLottie(View view) {
        Intent toActivity = new Intent(this, LottieToolBarActivity.class);
        this.startActivity(toActivity);
    }

    public void toBadge(View view) {
        Intent toActivity = new Intent(this, BadgeToolbarActivity.class);
        this.startActivity(toActivity);
    }

    public void toSearch(View view) {
        Intent toActivity = new Intent(this, SearchToolBarActivity.class);
        this.startActivity(toActivity);
    }

    public void toAnimation(View view) {
        Intent toActivity = new Intent(this, AnimationToolbarActivity.class);
        this.startActivity(toActivity);
    }

    public void toShare(View view) {
        Intent toActivity = new Intent(this, ShareToolbarActivity.class);
        this.startActivity(toActivity);
    }
}