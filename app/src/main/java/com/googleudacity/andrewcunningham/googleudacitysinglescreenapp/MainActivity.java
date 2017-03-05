package com.googleudacity.andrewcunningham.googleudacitysinglescreenapp;

import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Final Fields
    private final static String TAG = "MainActivity";
    private final static int VIBRATE_DURATION = 20;

    // Fields
    int[] images = {R.drawable.coffee1, R.drawable.coffee2, R.drawable.coffee3, R.drawable.coffee4};
    ImageButton previousButton, nextButton;

    // Views
    CollapsingToolbarLayout collapsingToolbarLayout;
    BottomNavigationView bottomNavigationView;
    ViewPager viewPager, fragmentPager;

    // Other
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        bindAllViews();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                vibrator.vibrate(VIBRATE_DURATION);
                switch(item.getItemId()){
                    case R.id.about_button:
                        fragmentPager.setCurrentItem(0, true);
                        return true;
                    case R.id.contact_button:
                        fragmentPager.setCurrentItem(1, true);
                        return true;
                }
                return false;
            }
        });


        fragmentPager.setAdapter(new myFragmentPager(getSupportFragmentManager()));
        fragmentPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView coffeePicture = new ImageView(container.getContext());
                coffeePicture.setImageResource(images[position]);
                coffeePicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
                ((ViewPager) container).addView(coffeePicture);
                return coffeePicture;
            }

            @Override
            public int getCount() {
                return images.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == (ImageView) object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((ImageView) object);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                showArrows(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        showArrows(viewPager.getCurrentItem());

    }

    private void bindAllViews() {

        // Bind views
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        fragmentPager = (ViewPager) findViewById(R.id.fragment_pager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav_bar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        previousButton = (ImageButton) findViewById(R.id.show_previous);
        nextButton = (ImageButton) findViewById(R.id.show_next);

        // Set OnClickListeners
        previousButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    private void showArrows(int position) {
        if (position == 0) {
            previousButton.setVisibility(View.INVISIBLE);
            nextButton.setVisibility(View.VISIBLE);
        } else if (position == images.length - 1) {
            previousButton.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.INVISIBLE);
        } else {
            previousButton.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.show_next:
                vibrator.vibrate(20);
                viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % images.length, true);
                break;
            case R.id.show_previous:
                vibrator.vibrate(20);
                if (viewPager.getCurrentItem() == 0)
                    viewPager.setCurrentItem(images.length, true);
                else
                    viewPager.setCurrentItem((viewPager.getCurrentItem() - 1) % images.length, true);
                break;
        }
    }

    private class myFragmentPager extends FragmentPagerAdapter{

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

        public myFragmentPager(FragmentManager fm) {
            super(fm);
            fragmentArrayList.add(new AboutFragment());
            fragmentArrayList.add(new ContactFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }


    }
}

