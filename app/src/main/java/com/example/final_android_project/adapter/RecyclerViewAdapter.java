package com.example.final_android_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;



import com.example.final_android_project.DetailsActivity;
import com.example.final_android_project.R;
import com.example.final_android_project.model.Chessplayer;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    public Context context;
    public List<Chessplayer> chessplayerList;
    public RecyclerViewAdapter(Context context, List<Chessplayer> chessplayerList) {
        this.context = context;
        this.chessplayerList = chessplayerList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chessplayer chessplayer = chessplayerList.get(position);
        holder.firstName.setText(chessplayer.getFirstName());
        holder.lastName.setText(chessplayer.getLastName());
        holder.eloRating.setText(chessplayer.getEloRating());
        byte []  arr  =  chessplayer.getImage();
        if(arr!=null){
        Bitmap bitmap = BitmapFactory.decodeByteArray(arr, 0, chessplayer.getImage().length);
        holder.image.setImageBitmap(bitmap);}
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView firstName;
        public TextView lastName;
        public TextView eloRating;
        public ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName =itemView.findViewById(R.id.main_activity_firstName);
            lastName = itemView.findViewById(R.id.main_activity_details_lastName);
            eloRating = itemView.findViewById(R.id.main_activity_details_eloRating);
            image = itemView.findViewById(R.id.main_activity_details_imageView);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Chessplayer chessplayer = chessplayerList.get(position);

            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("id",chessplayer.getId());
            context.startActivity(intent);
        }
    }



}
