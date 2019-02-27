package andreea.my.traveljournal.fragment_trips;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import andreea.my.traveljournal.ManageTripActivity;
import andreea.my.traveljournal.R;
import andreea.my.traveljournal.TripDetailsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {
    private RecyclerView mRecyclerViewTrips;
    FirebaseFirestore db;
    List<Trip> trips;

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        trips = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        mRecyclerViewTrips = (RecyclerView) view.findViewById(R.id.recyclerview_trips_fragment);

        //set the layout manager for the current recycler view
        mRecyclerViewTrips.setLayoutManager(new LinearLayoutManager(getActivity()));

        setUpFireBase();
        loadDataFromFirebase();

        mRecyclerViewTrips.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerViewTrips, new TripClickListener() {
                @Override
            public void onClick(View view, int position) {
                    Intent intent = new Intent(getActivity(), TripDetailsActivity.class);
                    startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ManageTripActivity.class);
                startActivity(intent);
            }
        }));
        return view;
    }

    private void loadDataFromFirebase() {
        db.collection("trips")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot documentSnapshot: task.getResult()) {
                            Log.e("ID:", documentSnapshot.getId());
                            Trip trip = new Trip(documentSnapshot.getString("trip_name")
                                    , documentSnapshot.getString("destination")
                                    , documentSnapshot.getString("ImageUrl")
                                    , documentSnapshot.getDouble("price")
                                    , documentSnapshot.getDouble("rating")
                                    , true
                                    );
                            trips.add(trip);
                        }
                        //create the adapter
                        TripAdapter tripAdapter = new TripAdapter(trips);

                        //set the adapter to the recycler view
                        mRecyclerViewTrips.setAdapter(tripAdapter);
                    }
                });
    }


    private void setUpFireBase() {

        db = FirebaseFirestore.getInstance();

    }


}
