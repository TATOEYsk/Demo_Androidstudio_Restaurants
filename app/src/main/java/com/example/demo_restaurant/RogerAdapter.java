package com.example.demo_restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RogerAdapter extends RecyclerView.Adapter<RogerAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private  ArrayList<RestaurantModel> RestaurantList;

    public RogerAdapter(Context ctx, ArrayList<RestaurantModel> RestaurantList){

        inflater = LayoutInflater.from(ctx);
        this.RestaurantList = RestaurantList;
    }
//    public RogerAdapter(ArrayList<RestaurantModel> restaurantList) {
//        RestaurantList = restaurantList;
//    }

    @NonNull
    @Override
    public RogerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        inflater = LayoutInflater.from(context);
        View restaurantView  = inflater.inflate(R.layout.rv_item, parent ,false);
        ViewHolder viewHolder  = new ViewHolder(restaurantView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RogerAdapter.ViewHolder holder, int position) {



        Picasso.get().load(RestaurantList.get(position).getImage_url()).into(holder.iv);
        holder.name.setText(RestaurantList.get(position).getName());
        holder.kind.setText(RestaurantList.get(position).getKind());
        holder.id.setText(RestaurantList.get(position).getid());
    }

    @Override
    public int getItemCount() {
        return RestaurantList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView kind, name, id;
        public ImageView iv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            kind = (TextView) itemView.findViewById(R.id.kind);
            iv = (ImageView) itemView.findViewById(R.id.iv);


        }
    }
}















//    private LayoutInflater inflater;
//    private ArrayList<RestaurantModel> rogerModelArrayList;
//
//    public RogerAdapter(Context ctx, ArrayList<RestaurantModel> rogerModelArrayList){
//
//        inflater = LayoutInflater.from(ctx);
//        this.rogerModelArrayList = rogerModelArrayList;
//    }
//
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = inflater.inflate(R.layout.rv_item, parent, false);
//        MyViewHolder holder = new MyViewHolder(view);
//
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//
//        Picasso.get().load(rogerModelArrayList.get(position).getImage_url()).into(holder.iv);
//        holder.name.setText(rogerModelArrayList.get(position).getName());
//        holder.kind.setText(rogerModelArrayList.get(position).getKind());
//    }
//
//    @Override
//    public int getItemCount() {
//        return rogerModelArrayList.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//        TextView kind, name;
//        ImageView iv;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//
//
//            name = (TextView) itemView.findViewById(R.id.name);
//            kind = (TextView) itemView.findViewById(R.id.kind);
//            iv = (ImageView) itemView.findViewById(R.id.iv);
//        }
//
//    }

