package com.android.hike.stimulation.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.hike.stimulation.R;
import com.android.hike.stimulation.pojo.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ItemViewHolder>{


    public Context mContext;
    List<Photo> photoList;
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.gallery_view_item, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        Photo photo = photoList.get(i);
       Uri imgyri = Uri.parse(photo.getPhotoUrl());

        Picasso.with(mContext).load( imgyri).resize(100,100).into(itemViewHolder.img);
        //itemViewHolder.img.setImageURI(imgyri);

    }

    public GalleryAdapter(Context ctx, List<Photo> photos)
    {
        mContext = ctx;
        photoList = photos;
    }


    public void SetData(List<Photo> photolist )
    {

        this.photoList = photolist;

    }
    @Override
    public int getItemCount() {

        return photoList.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public ItemViewHolder(@NonNull View itemView) {

            super(itemView);
            img = itemView.findViewById(R.id.photo);


        }
    }
}
