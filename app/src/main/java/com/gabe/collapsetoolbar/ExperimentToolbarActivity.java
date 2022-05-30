package com.gabe.collapsetoolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
//import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;

import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.gabe.collapsetoolbar.badge.BadgeActionProvider;
import com.google.android.material.badge.BadgeDrawable;




public class ExperimentToolbarActivity extends AppCompatActivity {


    private static final String TAG = ExperimentToolbarActivity.class.getSimpleName();
    Toolbar myToolbar;

    public void toolbarAnimation() {
        MenuItem item = myToolbar.getMenu().findItem(R.id.action_share);
        if (item == null) return;

        // define the animation for rotation
        Animation animation = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        //animRotate = AnimationUtils.loadAnimation(this, R.anim.rotation);

        animation.setRepeatCount(Animation.INFINITE);

        ImageView imageView = new ImageView(this);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_scan_qr_24dp));//.getIcon(this, MMEXIconFont.Icon.mmx_refresh));

        imageView.startAnimation(animation);
        item.setActionView(imageView);
    }


    private void addBadge() {

//        java.lang.IllegalArgumentException: The style on this component requires your app theme to be Theme.MaterialComponents (or a descendant).
//            at com.google.android.material.internal.ThemeEnforcement.checkTheme(ThemeEnforcement.java:241)
//        at com.google.android.material.internal.ThemeEnforcement.checkMaterialTheme(ThemeEnforcement.java:215)
//        at com.google.android.material.badge.BadgeDrawable.<init>(BadgeDrawable.java:464)
//        at com.google.android.material.badge.BadgeDrawable.createFromAttributes(BadgeDrawable.java:353)
//        at com.google.android.material.badge.BadgeDrawable.create(BadgeDrawable.java:321)
        Toolbar toolbar = myToolbar;

        Drawable navIcon = ContextCompat.getDrawable(this, R.drawable.ic_temp_24dp);//!!
        toolbar.setNavigationIcon(navIcon);//.navigationIcon = navIcon
        View navigationIconView = toolbar.getChildAt(0);
        if (navigationIconView instanceof ImageView) {
            ((ImageView) navigationIconView).setImageDrawable(navIcon);
        }


        // Avoid clipping a badge if it's displayed.
        toolbar.setClipChildren(false);//.clipChildren = false
        toolbar.setClipToPadding(false);//.clipTo.Padding = false

        BadgeDrawable badge = BadgeDrawable.create(this);

// now wait for layout to compute the actual position of the badge
        ViewTreeObserver treeObserver = toolbar.getViewTreeObserver();//.viewTreeObserver
        ViewTreeObserver.OnGlobalLayoutListener listener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // no need to listen more
                treeObserver.removeOnGlobalLayoutListener(this);

                Rect badgeBounds = new Rect();

                navigationIconView.getDrawingRect(badgeBounds);
                int vwidth = badgeBounds.width();
                int vheight = badgeBounds.height();
                int dwidth = navIcon.getIntrinsicWidth();//.intrinsicWidth
                int dHeight = navIcon.getIntrinsicHeight();//.intrinsicHeight
                int offsetX = (int) ((vwidth - dwidth) * 0.5f);
                int offsetY = (int) ((vheight - dHeight) * 0.5f);
                badge.setBounds(badgeBounds);
                badge.setHorizontalOffset(offsetX);//.horizontalOffset = offsetX
                badge.setVerticalOffset(offsetY);//.verticalOffset = offsetY
                badge.updateBadgeCoordinates(navigationIconView, null);
                navigationIconView.getOverlay().add(badge);//.overlay.add(badge);
            }
        };
        treeObserver.addOnGlobalLayoutListener(listener);

// do whatever with badge
        badge.setNumber(7);// = 7
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        而这个theme又是包含ActionBar的，可以在Activity中调用
//    requestWindowFeature(Window.FEATURE_NO_TITLE);，如果是继承在AppCompatActivity，调用

//    supportRequestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_common_toolbar);
        myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //使用自己的图标
//        myToolbar.setNavigationIcon(R.drawable.your_icon);
//        myToolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.ic_action_remove));


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //去掉TOOLbar的阴影  透明底时，阴影就明显了。
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            findViewById(R.id.appBarLayout).setOutlineProvider(null);
//        }


//        如果不想自定义图标，可以在获取到ActionBar对象之后，
//        设置actionBar.setDisplayHomeAsUpEnabled(true)
//    来启用Navigation功能，这样就可以使用系统自带的图标了。

        //使用自己的图标
//        myToolbar.setNavigationIcon(R.drawable.ic_action_other);

        //隐藏 ＴＯＯＴＬＢＡＲ
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

////        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
////        if (getSupportActionBar() != null) {
////            getSupportActionBar().hide();
////        }
//
//        setContentView(R.layout.activity_common_toolbar);
////        //设置标题布局为titlebar
////        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
////
////        addBtn = (ImageView) this.findViewById(R.id.barAdd);
////        searchBtn = (ImageView) this.findViewById(R.id.barSearch);
////
////        addBtn.setOnClickListener(this);
////        searchBtn.setOnClickListener(this);
//
//
//        // assigning ID of the toolbar to a variable
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
////        toolbar.getBackground().setAlpha(0);
//        // using toolbar as ActionBar
//        setSupportActionBar(toolbar);
//
//
//        // Display application icon in the toolbar
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.build_icon);//(R.drawable.android_icon);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//
////        // assigning ID of textView2 to a variable
////        textView = (TextView) findViewById(R.id.textView2);
////
////
////        // "on click" operations to be performed
////        textView.setOnClickListener(new View.OnClickListener() {
////            @Override
////
////            // incrementing the value of textView
////            public void onClick( View view ) {
////                count++;
////                textView.setText("" + count);
////            }
////        });
//
//        // Get a support ActionBar corresponding to this toolbar
//        ActionBar ab = getSupportActionBar();
//
//        // Enable the Up button
//        ab.setDisplayHomeAsUpEnabled(true);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.options, menu);
//        // ...
//
//        // Define the listener
//        MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {
//            @Override
//            public boolean onMenuItemActionCollapse(MenuItem item) {
//                // Do something when action item collapses
//                return true;  // Return true to collapse action view
//            }
//
//            @Override
//            public boolean onMenuItemActionExpand(MenuItem item) {
//                // Do something when expanded
//                return true;  // Return true to expand action view
//            }
//        };
//
//        // Get the MenuItem for the action item
//        MenuItem actionMenuItem = menu.findItem(R.id.myActionItem);
//
//        // Assign the listener to that action item
//        MenuItemCompat.setOnActionExpandListener(actionMenuItem, expandListener);
//
//        // Any other things you have to do when creating the options menu...
//
//        return true;
//    }

    @Override
    public void onResume() {
        super.onResume();
//        getSupportActionBar().setElevation(0);
    }
    TextView  txtViewCount;
    private void addBadge(Menu menu)
    {
        final View notificaitons = menu.findItem(R.id.action_share).getActionView();

           txtViewCount = (TextView) notificaitons.findViewById(R.id.txtCount);

           if(null!=txtViewCount){
               txtViewCount.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       updateHotCount(count++);
                   }
               });
           }

        notificaitons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    TODO
                updateHotCount(count++);
            }
        });
    }

    int count = 0;
    public void updateHotCount(final int new_hot_number) {
        count = new_hot_number;
        if (count < 0) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                    if(null==txtViewCount)
                        return;
                count++;
                if (count == 0){

                    txtViewCount.setVisibility(View.GONE);
                    //
                    }
                else {
                    txtViewCount.setVisibility(View.VISIBLE);
                    txtViewCount.setText(Integer.toString(count));
                    // supportInvalidateOptionsMenu();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        //折叠的菜单也显示图标。
        if (menu instanceof MenuBuilder) {
            MenuBuilder m = (MenuBuilder) menu;
            //noinspection RestrictedApi
            m.setOptionalIconsVisible(true);
        }
        //设置各项的图标。
//        for(int i=0;i <menu.size();i++ ){
//            MenuItem it  = menu.getItem(i);
//            it.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_place_holder));
//            it.getIcon().setTint(Color.BLUE);
//        }

//        Get a reference to the ShareActionProvider by calling getActionProvider()
//        and passing the share action's MenuItem. For example:

        MenuItem shareItem = menu.findItem(R.id.action_share);
//        ShareActionProvider myShareActionProvider =
//                (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
//
//
////        Create an intent with the Intent.ACTION_SEND action,
////        and attach the content shared by the activity. For example, the following intent shares an image:
//
//        Intent myShareIntent = new Intent(Intent.ACTION_SEND);
//        myShareIntent.setType("image/*");
//        myShareIntent.putExtra(Intent.EXTRA_STREAM, (Bundle) null);//myShareIntent.putExtra(Intent.EXTRA_STREAM, myImageUri);
////        Call setShareIntent() to attach this intent to the action provider:
//
//        myShareActionProvider.setShareIntent(myShareIntent);
//
////        When the content changes, modify the intent or create a new one,
////        and call setShareIntent() again. For example:
//
//        // Image has changed! Update the intent:
//        myShareIntent.putExtra(Intent.EXTRA_STREAM, (Bundle) null);//myShareIntent.putExtra(Intent.EXTRA_STREAM, myNewImageUri);
//        myShareActionProvider.setShareIntent(myShareIntent);
//
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView tToolbarSearchView = (SearchView) searchMenuItem.getActionView();
        tToolbarSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        tToolbarSearchView.setQueryHint("Type here to search");
        tToolbarSearchView.setMaxWidth(android.R.attr.width);

        tToolbarSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //getSearchResults(query); Also tried
                Log.i(TAG, String.format("query[%s]", tToolbarSearchView.getQuery().toString()));
//                getSearchResults(searchView.getQuery().toString());
                if (!tToolbarSearchView.isIconified()) {
                    tToolbarSearchView.setIconified(true);
                }
                searchMenuItem.collapseActionView();


//                menu.findItem(R.id.action_search).expandActionView();
//                // Force losing the focus to prevent the suggestions from appearing
//                tToolbarSearchView.clearFocus();
//                tToolbarSearchView.setFocusable(false);
//                tToolbarSearchView.setFocusableInTouchMode(false);
//                tToolbarSearchView.setQuery(query, false);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

//        toolbarAnimation();
//        addBadge(menu);
//        addBadge();

        initToolBar();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:

                Toast.makeText(this, "search button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_share:
                Toast.makeText(this, "remo button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_more:
                Toast.makeText(this, "more button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.other_item:
                Toast.makeText(this, "other button clicked", Toast.LENGTH_SHORT).show();

//                    initMenu(myToolbar).show();
                popWindow2(myToolbar);
                break;

            case android.R.id.home:
//                Navigation和Menu的关系
//
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


    private PopupMenu initMenu(View aView) {
        PopupMenu mPopupMenu = new PopupMenu(new ContextThemeWrapper(this, R.style.xAppTheme), aView);
        Menu menu = mPopupMenu.getMenu();

        if (menu instanceof MenuBuilder) {
            MenuBuilder m = (MenuBuilder) menu;
            //noinspection RestrictedApi
            m.setOptionalIconsVisible(true);
        }
        menu.add(R.string.action_favorite).setIcon(R.drawable.android_icon).setOnMenuItemClickListener(item -> {
            return true;
        });
        menu.add(R.string.action_search).setIcon(R.drawable.ic_menu_24dp).setOnMenuItemClickListener(item -> {
            return true;
        });

        return mPopupMenu;
    }

    private PopupWindow popWindow2(View aView) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_pop_menu, null, false);
        TextView popFirst = (TextView) view.findViewById(R.id.pop_menu_first);
        TextView popSecond = (TextView) view.findViewById(R.id.pop_menu_second);
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        popWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画
        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //                // 这里如果返回true的话，touch事件将被拦截
//                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                return false;
            }

        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效
        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(aView, 0, 6);

        //设置popupWindow里的按钮的事件
        popFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });
        popSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });


        TextView pop_third = (TextView) view.findViewById(R.id.pop_menu_third);
        pop_third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });

        TextView pop_fourth = (TextView) view.findViewById(R.id.pop_menu_fourth);
        pop_fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });
        return popWindow;
    }
//    OverFlow按钮显示图标
//    overflow中的按钮默认是不显示图标的，它由MenuBuilder这个类的setOptionalIconsVisible方法来决定，
//    如果我们在overflow被展开的时候给这个方法传入true，那么里面的每一个Action按钮对应的图标就都会显示出来了。


//    @Override
//    public boolean onMenuOpened(int featureId, Menu menu) {
//        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
//            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
//                try {
//                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
//                    m.setAccessible(true);
//                    m.invoke(menu, true);
//                } catch (Exception e) {
//                }
//            }
//        }
//        return super.onMenuOpened(featureId, menu);
//    }



    private BadgeActionProvider mBadgeActionProvider;
int badgeCnt = 0;
    private void initToolBar() {
        Toolbar mToolBar = this.myToolbar;
        //ToolBar 初始化 menu
//        mToolBar.inflateMenu(R.menu.toolbar_menu);
        MenuItem menuItem = mToolBar.getMenu().findItem(R.id.action_share);
        mBadgeActionProvider = (BadgeActionProvider) MenuItemCompat.getActionProvider(menuItem);
        mBadgeActionProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                badgeCnt++;
                mBadgeActionProvider.setBadge(badgeCnt);
//                Toast.makeText(CommonToolbarActivity.this,"来一下 \t now :"+badgeCnt,Toast.LENGTH_LONG).show();

            }
        });
    }
}