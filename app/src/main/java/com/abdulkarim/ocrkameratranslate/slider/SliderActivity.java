package com.abdulkarim.ocrkameratranslate.slider;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.abdulkarim.ocrkameratranslate.MainActivity;
import com.abdulkarim.ocrkameratranslate.R;

public class SliderActivity extends AppCompatActivity {

    private TextView back, next;
    private CardView step1, step2;
    private SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        back = findViewById(R.id.backTv);
        next = findViewById(R.id.nextTv);
        step1 = findViewById(R.id.step1Cv);
        step2 = findViewById(R.id.step2Cv);
        loadSlider();

        sharedPreference = new SharedPreference();
        if (sharedPreference.getStatus(SliderActivity.this, "status", null) != null) {
            Intent intent = new Intent(SliderActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void loadSlider(){
        SliderFragment1 tab1 = new SliderFragment1();
        ViewPager viewPager = (ViewPager) findViewById(R.id.sliderVp);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SliderFragment1(), "FRAG1");
        adapter.addFragment(new SliderFragment1(), "FRAG2");
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (viewPager.getCurrentItem() == 0){
                    back.setVisibility(View.GONE);
                    step1.setCardBackgroundColor(getResources().getColor(R.color.color_black));
                    step2.setCardBackgroundColor(getResources().getColor(R.color.color_white));
                    next.setText(getString(R.string.next));
                }
                if (viewPager.getCurrentItem() == 1){
                    back.setVisibility(View.VISIBLE);
                    step1.setCardBackgroundColor(getResources().getColor(R.color.color_white));
                    step2.setCardBackgroundColor(getResources().getColor(R.color.color_black));
                    next.setText(getString(R.string.finish));
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() == 1){
                    sharedPreference.setStatus(SliderActivity.this, "status", "1");
                    Intent intent = new Intent(SliderActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() +1);
                    if (viewPager.getCurrentItem() == 0){
                        back.setVisibility(View.GONE);
                        step1.setCardBackgroundColor(getResources().getColor(R.color.color_black));
                        step2.setCardBackgroundColor(getResources().getColor(R.color.color_white));
                        next.setText(getString(R.string.next));
                    }
                    if (viewPager.getCurrentItem() == 1){
                        back.setVisibility(View.VISIBLE);
                        step1.setCardBackgroundColor(getResources().getColor(R.color.color_white));
                        step2.setCardBackgroundColor(getResources().getColor(R.color.color_black));
                        next.setText(getString(R.string.finish));
                    }
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                if (viewPager.getCurrentItem() == 0){
                    back.setVisibility(View.GONE);
                    step1.setCardBackgroundColor(getResources().getColor(R.color.color_black));
                    step2.setCardBackgroundColor(getResources().getColor(R.color.color_white));
                    next.setText(getString(R.string.next));
                }
                if (viewPager.getCurrentItem() == 1){
                    back.setVisibility(View.VISIBLE);
                    step1.setCardBackgroundColor(getResources().getColor(R.color.color_white));
                    step2.setCardBackgroundColor(getResources().getColor(R.color.color_black));
                    next.setText(getString(R.string.next));
                }
            }
        });
    }
}
