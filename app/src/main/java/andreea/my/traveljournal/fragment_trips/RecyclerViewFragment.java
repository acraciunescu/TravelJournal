package andreea.my.traveljournal.fragment_trips;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import andreea.my.traveljournal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {
    private RecyclerView mRecyclerViewTrips;

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        mRecyclerViewTrips = (RecyclerView) view.findViewById(R.id.recyclerview_trips_fragment);

        //set the layout manager for the current recycler view
        mRecyclerViewTrips.setLayoutManager(new LinearLayoutManager(getActivity()));

        //create the adapter
        TripAdapter tripAdapter = new TripAdapter(getTripsList());

        //set the adapter to the recycler view
        mRecyclerViewTrips.setAdapter(tripAdapter);

        return view;
    }

    private List<Trip> getTripsList() {
        List<Trip> trips = new ArrayList<>();
        Trip trip1 = new Trip("Summer 2011", "France", "https://images.unsplash.com/photo-1510546462255-979b0e0ca1b5?w=800&q=60",
                200,10, true);
        Trip trip2 = new Trip("Summer 2011", "Romania", "https://images.unsplash.com/photo-1510546462255-979b0e0ca1b5?w=800&q=60",
                200,10, true);
        Trip trip3 = new Trip("Summer 2011", "France1", "https://images.unsplash.com/photo-1510546462255-979b0e0ca1b5?w=800&q=60",
                200,10, true);
        Trip trip4 = new Trip("Summer 2011", "France2", "https://images.unsplash.com/photo-1510546462255-979b0e0ca1b5?w=800&q=60",
                200,10, true);
        Trip trip5 = new Trip("Summer 2011", "France3", "https://images.unsplash.com/photo-1510546462255-979b0e0ca1b5?w=800&q=60",
                200,10, true);
        Trip trip6 = new Trip("Summer 2011", "France4", "https://images.unsplash.com/photo-1510546462255-979b0e0ca1b5?w=800&q=60",
                200,10, true);

        trips.add(trip1);
        trips.add(trip2);
        trips.add(trip3);
        trips.add(trip4);
        trips.add(trip5);
        trips.add(trip6);

        return trips;
    }
}
