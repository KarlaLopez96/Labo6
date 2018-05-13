package com.karla00058615.labo6.activities;

import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.karla00058615.labo6.R;
import com.karla00058615.labo6.adapters.PlanetsPagerAdapter;
import com.karla00058615.labo6.fragments.PlanetsListFragment;
import com.karla00058615.labo6.models.Planets;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PlanetsListFragment.onPlanetsSelected{

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    PlanetsPagerAdapter pagerAdapter;
    List<Planets> planetsList;
    PlanetsListFragment PlanetsFragment, favoriteFragment;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("planetsList", (ArrayList<? extends Parcelable>) planetsList);

        //saving fragments instance
        if (PlanetsFragment.isAdded())
            getSupportFragmentManager().putFragment(outState, "PlanetsFragment", PlanetsFragment);

        if (favoriteFragment.isAdded())
            getSupportFragmentManager().putFragment(outState, "favoriteFragment", favoriteFragment);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null || !savedInstanceState.containsKey("instance")){
            //setting up initial list
            planetsList = fillPlanets();

            //creating fragments
            PlanetsFragment = new PlanetsListFragment();
            favoriteFragment = new PlanetsListFragment();

            //configuring fragments
            PlanetsFragment.setList(planetsList);
            favoriteFragment.setList(favPlanets(planetsList));
        } else {
            planetsList = savedInstanceState.getParcelableArrayList("instance");
            PlanetsFragment = (PlanetsListFragment) getSupportFragmentManager().getFragment(savedInstanceState, "PlanetsFragment");
            favoriteFragment = (PlanetsListFragment) getSupportFragmentManager().getFragment(savedInstanceState, "favoriteFragment");
        }

        //setting up the Toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        //setting up the PagerAdapter
        pagerAdapter = new PlanetsPagerAdapter(getSupportFragmentManager(),this);
        pagerAdapter.addItem("Planets", PlanetsFragment);
        pagerAdapter.addItem("Favorites", favoriteFragment);

        //setting up the Viewpager
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                List<Planets> newList = position == 0 ? planetsList : favPlanets(planetsList);
                PlanetsListFragment listFragment = ((PlanetsListFragment)pagerAdapter.getItem(position));
                if (listFragment != null)
                    listFragment.updateList(newList);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //setting up the Tablayout
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    private ArrayList<Planets> fillPlanets(){
        ArrayList<Planets> l = new ArrayList<>();
        l.add(new Planets(1, "Mercurio", false));
        l.add(new Planets(1, "Venus",false));
        l.add(new Planets(1, "La Tierra",false));
        l.add(new Planets(1, "Marte",false));

        return l;
    }

    private ArrayList<Planets> favPlanets(List<Planets> planets1){
        ArrayList<Planets> favs = new ArrayList<>();

        for (Planets planets : planets1){
            if (planets.isFavorite()) favs.add(planets);
        }

        return favs;
    }
}
