package andreea.my.traveljournal.fragment_trips;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import andreea.my.traveljournal.R;

public class TripsViewHolder extends RecyclerView.ViewHolder {
    public ImageView mImageView;
    public TextView mTextViewSeason;
    public TextView mTextViewLocation;
    public TextView mTextViewPriceAndRating;
    public ToggleButton mToggleButtonFavourite;

    public TripsViewHolder(@NonNull View itemView) {
        super(itemView);

        mImageView = itemView.findViewById(R.id.imageview_trip);
        mTextViewSeason = itemView.findViewById(R.id.textview_season);
        mTextViewLocation = itemView.findViewById(R.id.textview_location);
        mTextViewPriceAndRating = itemView.findViewById(R.id.textview_price_rating);
        mToggleButtonFavourite = itemView.findViewById(R.id.togglebutton_favourite);

    }


}
