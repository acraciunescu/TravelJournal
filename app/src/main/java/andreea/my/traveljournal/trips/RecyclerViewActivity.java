package andreea.my.traveljournal.trips;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import andreea.my.traveljournal.R;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerViewTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initView();
    }

    private void initView() {
        mRecyclerViewTrips = findViewById(R.id.recyclerview_trips);

        //set the layout manager for the current recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewTrips.setLayoutManager(layoutManager);

        //create the adapter
        TripAdapter tripAdapter = new TripAdapter(getTripsList());

        //set the adapter to the recycler view
        mRecyclerViewTrips.setAdapter(tripAdapter);
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
        Trip trip7 = new Trip("Summer 2011", "France5", "https://images.unsplash.com/photo-1510546462255-979b0e0ca1b5?w=800&q=60",
                200,10, true);
        Trip trip8 = new Trip("Summer 2011", "France6", "https://images.unsplash.com/photo-1510546462255-979b0e0ca1b5?w=800&q=60",
                200,10, true);
        Trip trip9 = new Trip("Summer 2011", "France7", "https://images.unsplash.com/photo-1510546462255-979b0e0ca1b5?w=800&q=60",
                200,10, true);
        Trip trip10 = new Trip("Summer 2011", "France8", "https://images.unsplash.com/photo-1510546462255-979b0e0ca1b5?w=800&q=60",
                200,10, true);
        trips.add(trip1);
        trips.add(trip2);
        trips.add(trip3);
        trips.add(trip4);
        trips.add(trip5);
        trips.add(trip6);
        trips.add(trip7);
        trips.add(trip8);
        trips.add(trip9);
        trips.add(trip10);
        return trips;
    }
}
