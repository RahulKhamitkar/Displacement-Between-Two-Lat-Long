package com.rahul.two.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahul.two.Database.Details;
import com.rahul.two.R;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    List<Details> details;

    public DetailAdapter(List<Details> details) {
        this.details = details;
    }

    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_details_layout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int i) {

        holder.from.setText(details.get(i).getFrom());
        holder.to.setText(details.get(i).getTo());
        holder.distance.setText(details.get(i).getDistance());
    }

    @Override
    public int getItemCount() {
        return details.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView from;
        public TextView to;
        public TextView distance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            from = itemView.findViewById(R.id.from);
            to = itemView.findViewById(R.id.to);
            distance = itemView.findViewById(R.id.distance);

        }
    }
}
