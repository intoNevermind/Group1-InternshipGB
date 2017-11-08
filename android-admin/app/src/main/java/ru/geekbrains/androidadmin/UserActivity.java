package ru.geekbrains.androidadmin;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import ru.geekbrains.androidadmin.adapters.MyViewPagerAdapter;
import ru.geekbrains.androidadmin.fragments.PeopleFragment;

/**
 * Created by Loskutov.Viktor on 08.11.2017.
 */

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);

        MyViewPagerAdapter vpAdapter = new MyViewPagerAdapter(getSupportFragmentManager());

        vpAdapter.addFragment(new PeopleFragment(), "Личности");
        vpAdapter.addFragment(new PeopleFragment(), "Ключевые слова");
        vpAdapter.addFragment(new PeopleFragment(), "Сайты");
        viewPager.setAdapter(vpAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
