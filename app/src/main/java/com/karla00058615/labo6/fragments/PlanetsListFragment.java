package com.karla00058615.labo6.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karla00058615.labo6.R;
import com.karla00058615.labo6.adapters.PlanetsAdapter;
import com.karla00058615.labo6.adapters.PlanetsPagerAdapter;
import com.karla00058615.labo6.models.Planets;

import java.util.ArrayList;
import java.util.List;

public class PlanetsListFragment extends Fragment {
    RecyclerView recyclerView;
    PlanetsAdapter planetsAdapter;
    List<Planets> planetsList;
    onPlanetsSelected mCallBack;


    public PlanetsListFragment() {
        // Required empty public constructor
    }

    public interface onPlanetsSelected{

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View  v = inflater.inflate(R.layout.fragment_planets_list, container, false);

       if (savedInstanceState != null){
           planetsAdapter =savedInstanceState.getParcelable("adapter");
       }else{
           planetsAdapter = new PlanetsAdapter(getContext(), planetsList);
       }

       recyclerView =v.findViewById(R.id.recyclerView);
       recyclerView.setAdapter(planetsAdapter);
       recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
       return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mCallBack = (onPlanetsSelected) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement onPlanetsSelectedListener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState != null){
            planetsList = savedInstanceState.getParcelableArrayList("planetList");
            planetsAdapter = savedInstanceState.getParcelable("adapter");
            planetsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            planetsList = savedInstanceState.getParcelableArrayList("planetList");
            planetsAdapter = savedInstanceState.getParcelable("adapter");
            planetsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("planetList",(ArrayList<? extends Parcelable>)planetsList);
        outState.putParcelable("adapter",planetsAdapter);
    }

    public void setList (List<Planets> l){
        this.planetsList = l;
    }

    public void updateList(List<Planets> l){
        if(planetsAdapter != null)
            planetsAdapter.updateList(l);
    }

}
