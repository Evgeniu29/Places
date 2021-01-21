package com.genius.testtask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Place> places;

    public PlaceAdapter(Context ctx, List<Place> places){
        this.inflater = LayoutInflater.from(ctx);
        this.places = places;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.detail,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // bind the data
        holder.places.setText(places.get(position).getId()+ "." + " " +  places.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView places;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            places= itemView.findViewById(R.id.places);

        }
    }
}