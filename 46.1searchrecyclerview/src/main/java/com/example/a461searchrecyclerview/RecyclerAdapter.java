package com.example.a461searchrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by comp on 3/26/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    ArrayList<Actors> arrayList_actors = new ArrayList<>();

    RecyclerAdapter(ArrayList<Actors> arrayList_actors) {

        this.arrayList_actors = arrayList_actors;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        //return null;
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.imageView.setImageResource(arrayList_actors.get(position).getActors_id());
        holder.textView.setText(arrayList_actors.get(position).getNames());

    }

    @Override
    public int getItemCount() {
        // return 0;
        return arrayList_actors.size();
    }

    public void setFilter(ArrayList<Actors> newList) {

        arrayList_actors = new ArrayList<>();
        arrayList_actors.addAll(newList);
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.ImageView);
            textView = (TextView) itemView.findViewById(R.id.TxtView);
        }
    }
}
