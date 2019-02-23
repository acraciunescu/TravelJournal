package andreea.my.traveljournal.trips;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;

import java.util.List;

import andreea.my.traveljournal.R;

public class TripAdapter extends RecyclerView.Adapter<TripsViewHolder> {
    private List<Trip> mTrips;

    public TripAdapter(List<Trip> mTrips) {
        this.mTrips = mTrips;
    }

    @NonNull
    @Override
    public TripsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View tripView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trip_item, viewGroup, false);

        return new TripsViewHolder(tripView);
    }

    @Override
    public void onBindViewHolder(@NonNull TripsViewHolder tripsViewHolder, int i) {
        Trip trip = mTrips.get(i);
        tripsViewHolder.mTextViewSeason.setText(trip.getmSeason());
        tripsViewHolder.mTextViewLocation.setText(trip.getmLocationName());
        tripsViewHolder.mTextViewPriceAndRating.setText(trip.getmPrice()+" /"+trip.getmRating());
        if(trip.ismBookmark())
            tripsViewHolder.mToggleButtonFavourite.setBackgroundResource(R.drawable.ic_bookmark_black_24dp);
        else
            tripsViewHolder.mToggleButtonFavourite.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);
        Picasso.get().load(trip.getmPicture()).into(tripsViewHolder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mTrips.size();
    }
}
