package com.gabe.collapsetoolbar.lottie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieListener;
import com.airbnb.lottie.LottieTask;
import com.gabe.collapsetoolbar.R;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LottieToolBarActivity extends AppCompatActivity {

    private static final String TAG = LottieToolBarActivity.class.getSimpleName();

    private LottieDrawable animateCameraIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_tool_bar);
//       loadOverlayIcon() ;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.lottie_toolbar);
        //不设置不显示 设置好的TITLE栏。 只人标题。
        setSupportActionBar(myToolbar);

        //使用自己的图标
//        myToolbar.setNavigationIcon(R.drawable.ic_action_other);
//        myToolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_action_remove));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

//        playLottieIndContainer();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lottie_toolbar_menu, menu);
        //折叠的菜单也显示图标。
        if (menu instanceof MenuBuilder) {
            MenuBuilder m = (MenuBuilder) menu;
            //noinspection RestrictedApi
            m.setOptionalIconsVisible(true);
        }

        MenuItem action_lottie_second = menu.findItem(R.id.action_lottie_second);

        final View giftView2 = action_lottie_second.getActionView();
        giftView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LottieToolBarActivity.this, "lottie action_lottie_second click", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "lottie action_lottie_second click ");
                View xx = giftView2.findViewById(R.id.watch_animation);
                if(xx instanceof  LottieAnimationView){
                    ((LottieAnimationView) xx).playAnimation();
                }
            }
        });

        MenuItem firstItem = menu.findItem(R.id.action_lottie_first);

        View giftView = firstItem.getActionView();
        giftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // todo
                Log.i(TAG, "lottie click ");
                Toast.makeText(LottieToolBarActivity.this, "lottie click ", Toast.LENGTH_SHORT).show();
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //用了动画 或lottie不进这里，原因不明，现在用在外面设置onClickListener的方法去解决。
        switch (item.getItemId()) {
            case R.id.action_lottie_first:
                Toast.makeText(this, "action_anim_first button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_lottie_second:
                Toast.makeText(this, "action_anim_second button clicked", Toast.LENGTH_SHORT).show();
                final View giftView2 = item.getActionView();
                View xx = giftView2.findViewById(R.id.watch_animation);
                if(xx instanceof  LottieAnimationView){
                    ((LottieAnimationView) xx).playAnimation();
                }
                break;
            case R.id.action_lottie_third:
                Toast.makeText(this, "action_anim_third button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_lottie_fourth:
                Toast.makeText(this, "action_anim_fourth button clicked", Toast.LENGTH_SHORT).show();
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

    private void playLottieIndContainer() {
        //        Step 3: Now include the XML code into the layout file where you want to show the animation.
//        Here the file name for JSON file is assumed to be animation.
//        Different methods through which Lottie can take JSON files
//
//        A JSON animation: src/main/res/raw
//        A JSON file: src/main/assets
//        A zip file: src/main/assets

// Declaring the animation view
        LottieAnimationView animationView
                = findViewById(R.id.lottie_view_thumbup);
        animationView
                .addAnimatorUpdateListener(
                        (animation) -> {
                            // Do something.
                        });
        animationView
                .playAnimation();
        if (animationView.isAnimating()) {
            // Do something.
        }
        // Custom animation speed or duration.
        ValueAnimator animator
                = ValueAnimator.ofFloat(0f, 1f);
        animator
                .addUpdateListener(animation -> {
                    animationView
                            .setProgress(
                                    (Float) animation
                                            .getAnimatedValue());
                });
        animator.start();
    }

    private void loadOverlayIcon() {
        CountDownLatch latch = new CountDownLatch(1);
        LottieTask<LottieComposition> task = LottieCompositionFactory.fromRawRes(this, R.raw.gradient_animated_background);

        task.addListener(new LottieListener<LottieComposition>() {
            @Override
            public void onResult(LottieComposition result) {
                Log.i(TAG, "Loaded camera animation: " + result);
                animateCameraIcon = new LottieDrawable();
                animateCameraIcon.setComposition(result);
                animateCameraIcon.setRepeatCount(LottieDrawable.INFINITE);
//                animateCameraIcon.setScale(5);
                animateCameraIcon.playAnimation();
                animateCameraIcon.setSpeed(0.7f);

                if (animateCameraIcon != null) {
//                Toolbar myToolbar = (Toolbar) findViewById(R.id.anim_toolbar);
//                myToolbar.findViewById(R.id.action_anim_second);
//                myToolbar.setOverflowIcon(animateCameraIcon);
//                animateCameraIcon.playAnimation();
                }
                latch.countDown();
            }
        });

        task.addFailureListener(new LottieListener<Throwable>() {
            @Override
            public void onResult(Throwable result) {
                Log.e(TAG, "Error loading camera animation: " + result);
                latch.countDown();
            }
        });
        try {
            latch.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}