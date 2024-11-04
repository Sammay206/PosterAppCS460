package com.example.posterapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class PosterAdaptor extends RecyclerView.Adapter<PosterAdaptor.PosterViewHolder>{

    private List<Poster> posterList;
    private PosterListener postersListener;

    public List<Poster> getSelectedPosters(){
        List<Poster> selectedPosters = new ArrayList<>();
        for(Poster poster : this.posterList){
            selectedPosters.add(poster);
        }
        return selectedPosters;
    }

    public PosterAdaptor(List<Poster> posterList, PosterListener postersListener) {
        this.posterList = posterList;
        this.postersListener = postersListener;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPoster(posterList.get(position));
    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }

    class PosterViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layoutPosters;
        View viewBackground;
        RoundedImageView imagePoster;
        TextView textName, textDirector, textSynopsis;
        RatingBar ratingBar;
        ImageView imageSelected;
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutPosters = itemView.findViewById(R.id.layoutPoster);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePoster);
            textName = itemView.findViewById(R.id.textName);
            textDirector = itemView.findViewById(R.id.textDirector);
            textSynopsis = itemView.findViewById(R.id.textSynopsis);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }

        void bindPoster(final Poster poster) {
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textDirector.setText(poster.director);
            textSynopsis.setText(poster.synopsis);
            ratingBar.setRating(poster.rating);
            if (poster.isSelected) {
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            } else {
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }

            layoutPosters.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(poster.isSelected){
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                        if(getSelectedPosters().isEmpty()){
                            postersListener.onPosterAction(false);
                        }
                    }else{
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected = true;
                        postersListener.onPosterAction(true);
                    }
                }
            });
        }
    }
}
