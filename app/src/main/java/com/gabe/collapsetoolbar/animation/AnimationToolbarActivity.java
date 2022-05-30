package com.gabe.collapsetoolbar.animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.gabe.collapsetoolbar.R;
import com.gabe.collapsetoolbar.lottie.LottieToolBarActivity;

public class AnimationToolbarActivity extends AppCompatActivity {
    private static final String TAG = AnimationToolbarActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_toolbar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.animate_toolbar);
        //不设置不显示 设置好的TITLE栏。 只人标题。
        setSupportActionBar(myToolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.animation_toolbar_menu, menu);
        //折叠的菜单也显示图标。
        if (menu instanceof MenuBuilder) {
            MenuBuilder m = (MenuBuilder) menu;
            //noinspection RestrictedApi
            m.setOptionalIconsVisible(true);
        }
        //设置各项的图标。
        for(int i=0;i <menu.size();i++ ){
            MenuItem it  = menu.getItem(i);
//            it.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_refresh_24dp));
            it.getIcon().setTint(Color.BLUE);
        }
        toolbarAnimation();
        return super.onCreateOptionsMenu(menu);
    }


    public void toolbarAnimation() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.animate_toolbar);
        MenuItem item = myToolbar.getMenu().findItem(R.id.action_animation_second);
        if (item == null) return;

        // define the animation for rotation
        Animation animation = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        //animRotate = AnimationUtils.loadAnimation(this, R.anim.rotation);

        animation.setRepeatCount(Animation.INFINITE);

        ImageView imageView = new ImageView(this);
        Drawable refreshIc = ContextCompat.getDrawable(this, R.drawable.ic_refresh_24dp);
        refreshIc.setTint(Color.BLUE);
        imageView.setImageDrawable(refreshIc);//.getIcon(this, MMEXIconFont.Icon.mmx_refresh));

        imageView.startAnimation(animation);
        item.setActionView(imageView);
        item.getIcon().setTint(Color.BLUE);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_animation_second:
                Toast.makeText(this, "action_animation_second button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_animation_first:
                Toast.makeText(this, "action_animation_first button clicked", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
//                Navigation和Menu的关系
//                实际上Navigation也属于menu，它的id是android.R.id.home，可以在onOptionsItemSelected中对它进行监听
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                } else {
                    upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}