package com.gabe.collapsetoolbar.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import androidx.core.content.ContextCompat;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.gabe.collapsetoolbar.R;

public class SearchToolBarActivity extends AppCompatActivity {

    private static final String TAG = SearchToolBarActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tool_bar);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_toolbar_menu, menu);

        //设置各项的图标。
        for(int i=0;i <menu.size();i++ ){
            MenuItem it  = menu.getItem(i);
            it.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_refresh_24dp));
            it.getIcon().setTint(Color.BLUE);
        }

        initSearchMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initSearchMenu(Menu menu){
        MenuItem searchMenuItem = menu.findItem(R.id.action_search_tb_first);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView tToolbarSearchView = (SearchView) searchMenuItem.getActionView();
        tToolbarSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

//        EditText txtSearch = ((EditText)tToolbarSearchView.findViewById(androidx.appcompat.R.id.search_src_text));
//        txtSearch.setHintTextColor(Color.LTGRAY);
//        txtSearch.setTextColor(Color.BLUE);


        tToolbarSearchView.setQueryHint("Type here to search");
        tToolbarSearchView.setMaxWidth(android.R.attr.width);

        tToolbarSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //getSearchResults(query); Also tried
                Log.i(TAG, String.format("query[%s]", tToolbarSearchView.getQuery().toString()));
                if (!tToolbarSearchView.isIconified()) {
                    tToolbarSearchView.setIconified(true);
                }
                searchMenuItem.collapseActionView();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search_tb_first:
                Toast.makeText(this, "search button clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_search_tb_refresh:
                Toast.makeText(this, "refresh button clicked", Toast.LENGTH_SHORT).show();
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


//    27
//
//    I wanted to do something similar. I finally had to find the TextView among the SearchView children:
//
//            for (TextView textView : findChildrenByClass(searchView, TextView.class)) {
//        textView.setTextColor(Color.WHITE);
//    }
//    If you want the util method:
//
//    public static <V extends View> Collection<V> findChildrenByClass(ViewGroup viewGroup, Class<V> clazz) {
//
//        return gatherChildrenByClass(viewGroup, clazz, new ArrayList<V>());
//    }
//
//    private static <V extends View> Collection<V> gatherChildrenByClass(ViewGroup viewGroup, Class<V> clazz, Collection<V> childrenFound) {
//
//        for (int i = 0; i < viewGroup.getChildCount(); i++)
//        {
//            final View child = viewGroup.getChildAt(i);
//            if (clazz.isAssignableFrom(child.getClass())) {
//                childrenFound.add((V)child);
//            }
//            if (child instanceof ViewGroup) {
//                gatherChildrenByClass((ViewGroup) child, clazz, childrenFound);
//            }
//        }
//
//        return childrenFound;
//    }
}