package com.karla00058615.labo6.adapters;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.karla00058615.labo6.R;
import com.karla00058615.labo6.models.Planets;

import java.util.List;

/**
 * Created by Karla on 13/5/2018.
 */

public class PlanetsAdapter extends RecyclerView.Adapter<PlanetsAdapter.PlanetsViewHolder> implements Parcelable {

    Context Ctx;
    List<Planets> planetsList;

    public PlanetsAdapter(Context Ctx, List<Planets> planetsList){
        this.Ctx = Ctx;
        this.planetsList = planetsList;
    }

    protected PlanetsAdapter (Parcel in){
        planetsList = in.createTypedArrayList(Planets.CREATOR);
    }

    public static final Creator<PlanetsAdapter> CREATOR = new Creator<PlanetsAdapter>() {
        @Override
        public PlanetsAdapter createFromParcel(Parcel in) {
            return new PlanetsAdapter(in);
        }

        @Override
        public PlanetsAdapter[] newArray(int size) {
            return new PlanetsAdapter[size];
        }
    };

    @NonNull
    @Override
    public PlanetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(Ctx).inflate(R.layout.planet_cardview, parent, false);
        PlanetsViewHolder viewHolder = new PlanetsViewHolder(v);
        return viewHolder;
    }

    public void updateList (List<Planets> l){
        planetsList = l;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final PlanetsViewHolder holder, final int position) {
        final Planets planets = planetsList.get(position);

        holder.planetsTitle.setText(planetsList.get(position).getName());
        holder.favImage.setImageResource(planets.isFavorite() ? android.R.drawable.btn_star_big_on: android.R.drawable.btn_star_big_off);
        holder.favImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (planets.isFavorite()){
                    holder.favImage.setImageResource(android.R.drawable.btn_star_big_off);
                    planetsList.get(position).setFavorite(false);
                }else{
                    holder.favImage.setImageResource(android.R.drawable.btn_star_big_on);
                    planetsList.get(position).setFavorite(true);
                }
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        if(planetsList != null)
            return planetsList.size();
        else
            return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(planetsList);
    }

    protected class PlanetsViewHolder extends RecyclerView.ViewHolder{
        ImageView planetImage, favImage;
        TextView planetsTitle;

        public PlanetsViewHolder(View itemView) {
            super(itemView);

            planetImage = itemView.findViewById(R.id.planet_Image);
            planetsTitle = itemView.findViewById(R.id.planetsTitle);
            favImage = itemView.findViewById(R.id.favIcon);
        }
    }
}
