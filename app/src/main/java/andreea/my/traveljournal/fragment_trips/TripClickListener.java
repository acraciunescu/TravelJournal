package andreea.my.traveljournal.fragment_trips;

import android.view.View;

public interface TripClickListener {
    void onClick(View view, int position);
    void onLongClick(View view, int position);
}
