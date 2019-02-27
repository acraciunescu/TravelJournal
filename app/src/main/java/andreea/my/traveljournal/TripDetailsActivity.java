package andreea.my.traveljournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import andreea.my.traveljournal.fragment_trips.RecyclerViewFragment;

public class TripDetailsActivity extends AppCompatActivity {

    FirebaseFirestore db;

    private SeekBar mSeekBarPrice;
    private EditText mEditTextDestination;
    private EditText mEditTextTripName;
    private Button mButtonStartDate;
    private Button mButtonEndDate;
    private RatingBar mRatingBar;
    private ImageView mImageView;
    private TextView mTextViewType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        initView();

        setUpFirebase();

        putDataInView();
    }

    private void putDataInView() {
        Intent intent = getIntent();
        String message = intent.getStringExtra(RecyclerViewFragment.TRIP_KEY);
        if (message != null && !message.isEmpty()) {
            DocumentReference docRef = db.collection("trips").document(message);
            docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    mEditTextTripName.setText(documentSnapshot.getString("trip_name"));
                    mEditTextDestination.setText(documentSnapshot.getString("destination"));
                    Picasso.get().load(documentSnapshot.getString("ImageUrl")).into(mImageView);

                    if(documentSnapshot.get("type", int.class)==1) {
                        mTextViewType.setText("City Break");
                    }
                    else if((long) documentSnapshot.get("type", int.class)==2) {
                        mTextViewType.setText("Sea side");
                    }
                    else if((long) documentSnapshot.get("type", int.class)==3) {
                        mTextViewType.setText("Mountain");
                    }

                    mButtonStartDate.setText(java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(documentSnapshot.getDate("start_date")));
                    mButtonEndDate.setText(java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(documentSnapshot.getDate("end_date")));
                    mSeekBarPrice.setProgress(documentSnapshot.get("price", int.class));
                    mRatingBar.setRating(documentSnapshot.get("rating" , float.class));
                }
            });
        }
    }

    private void setUpFirebase() {
        db = FirebaseFirestore.getInstance();
    }

    private void initView() {
        mSeekBarPrice = findViewById(R.id.seekbar_readonly);
        mButtonEndDate = findViewById(R.id.button_enddate_readonly);
        mButtonStartDate = findViewById(R.id.button_startdate_readonly);
        mRatingBar = findViewById(R.id.ratingbar_readonly);
        mEditTextDestination = findViewById(R.id.edittext_destination_readonly);
        mEditTextTripName = findViewById(R.id.edittext_tripname_readonly);
        mImageView = findViewById(R.id.imageView_readonly);
        mTextViewType = findViewById(R.id.textview_triptypeshow);
        mRatingBar = findViewById(R.id.ratingbar_readonly);


        mSeekBarPrice.setEnabled(false);
        mEditTextTripName.setEnabled(false);
        mEditTextDestination.setEnabled(false);
        mButtonStartDate.setEnabled(false);
        mButtonEndDate.setEnabled(false);

    }

}
