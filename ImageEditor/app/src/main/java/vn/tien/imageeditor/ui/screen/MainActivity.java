package vn.tien.imageeditor.ui.screen;

import android.app.SearchManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import vn.tien.imageeditor.R;
import vn.tien.imageeditor.ui.fragment_collections.FragmentCollections;
import vn.tien.imageeditor.ui.fragment_home.FragmentHome;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private static final String TAG = MainActivity.class.getSimpleName();
    private SearchView mSearchView = null;
    private FragmentHome mFragmentHome = new FragmentHome();
    private FragmentCollections mFragmentCollections = new FragmentCollections();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }


    private void initView() {

        mToolbar = findViewById(R.id.tool_bar);
        mToolbar.setTitle("Picture Nice");
        mToolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        setSupportActionBar(mToolbar);


        mViewPager = findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

        mTabLayout = findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void setupViewPager(ViewPager mViewPager) {
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(mFragmentHome, "Home");
        pagerAdapter.addFragment(mFragmentCollections, "Collections");

        mViewPager.setAdapter(pagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (mSearchView != null && !mSearchView.isIconified()) {
                    mSearchView.setIconified(true);
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }


    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mTittle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTittle.get(position);
        }

        public void addFragment(Fragment fragment, String tittle) {
            mFragments.add(fragment);
            mTittle.add(tittle);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(this.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.search);
        mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();
        if (searchItem != null) {
            mSearchView = (SearchView) searchItem.getActionView();
        }
        if (mSearchView != null) {
            mSearchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
        }
        mSearchView.setIconifiedByDefault(true);
        MenuItemCompat.expandActionView(searchItem);
        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                PagerAdapter pagerAdapter = mViewPager.getAdapter();
                for (int i = 0; i < pagerAdapter.getCount(); i++) {
                    Fragment viewFragment = (Fragment) mViewPager.getAdapter().instantiateItem(mViewPager, i);
                    if (viewFragment != null && viewFragment.isAdded()){
                        if (viewFragment instanceof FragmentHome){
                            mFragmentHome = (FragmentHome) viewFragment;
                            if (mFragmentHome != null){
                                mFragmentHome.beginSearch(newText);
                            }
                        }
                        if (viewFragment instanceof FragmentCollections){
                            mFragmentCollections = (FragmentCollections) viewFragment;
                            if (mFragmentCollections !=null){
                                mFragmentCollections.beginSearch(newText);
                            }
                        }
                    }
                }
                return false;
            }
        };
        mSearchView.setOnQueryTextListener(queryTextListener);
        return super.onCreateOptionsMenu(menu);
    }
}


