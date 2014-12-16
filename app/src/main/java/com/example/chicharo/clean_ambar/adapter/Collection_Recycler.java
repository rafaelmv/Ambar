package com.example.chicharo.clean_ambar.adapter;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.chicharo.clean_ambar.Activities.ObjectsActivity;
import com.example.chicharo.clean_ambar.util.NetworkImageViewCircle;
import com.example.chicharo.clean_ambar.R;
import com.example.chicharo.clean_ambar.app.AppController;
import com.example.chicharo.clean_ambar.models.CollectionModel;

import java.util.ArrayList;

/**
 * Created by chicharo on 8/12/14.
 * Aquí está el onClick en onBindViewHolder. No encontré algo más conveniente
 */
public class Collection_Recycler extends RecyclerView.Adapter<Collection_Recycler.viewHolder> {
    private Activity myActivity;
    private ArrayList<CollectionModel> mDataset;
    ImageLoader imageLoader;
    CollectionModel m;

    // Provide a suitable constructor (depends on the kind of dataset)
    public Collection_Recycler(Activity activity,ArrayList<CollectionModel> myDataset) {
        this.myActivity = activity;
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Collection_Recycler.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_adapter, parent, false);
        // set the view's size, margins, paddings and layout parameters
        viewHolder vh = new viewHolder(v);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        m = mDataset.get(position);

        //thumbnail
        //"http://lax102.fm/wp-content/uploads/2014/11/Marina-and-the-Diamonds-Froot-2014-1000x1000-Official.jpg"
        holder.thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
        // title
        holder.title.setText(m.getTitle());
        //holder.title.setText("Froot");

        // rating
        holder.rating.setText("Rating: " + String.valueOf(m.getRating()));
        //holder.rating.setText("5/5");

        // genre
        String genreStr = "";
        for (String str : m.getGenre()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,genreStr.length() - 2) : genreStr;
        holder.genre.setText(genreStr);
        //holder.genre.setText("MK");

        // release year
        holder.year.setText(String.valueOf(m.getYear()));
        //holder.year.setText("2014");

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Adapter","onClick");
                Intent i = new Intent(myActivity,ObjectsActivity.class);
                myActivity.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
        //mDataset.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        NetworkImageViewCircle thumbNail;
        TextView title;
        TextView rating;
        TextView genre;
        TextView year;
        CardView card;

        public viewHolder(View vv){
            super(vv);
            thumbNail = (NetworkImageViewCircle)vv.findViewById(R.id.thumbnailCollection);
            title = (TextView) vv.findViewById(R.id.title);
            rating = (TextView) vv.findViewById(R.id.rating);
            genre = (TextView) vv.findViewById(R.id.genre);
            year = (TextView) vv.findViewById(R.id.releaseYear);
            card = (CardView)vv.findViewById(R.id.card_view);
        }
    }
    public void add(int position, CollectionModel item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(CollectionModel item) {
        int position = mDataset.indexOf(item); // a ver si funciona
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

}