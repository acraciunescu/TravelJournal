package andreea.my.traveljournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;

public class TripDetailsActivity extends AppCompatActivity {

    private SeekBar mSeekBarPrice;
    private EditText mEditTextDestination;
    private EditText mEditTextTripName;
    private Button mButtonStartDate;
    private Button mButtonEndDate;
    private RatingBar mRatingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        mSeekBarPrice = findViewById(R.id.seekbar_readonly);
        mButtonEndDate = findViewById(R.id.button_enddate_readonly);
        mButtonStartDate = findViewById(R.id.button_startdate_readonly);
        mRatingBar = findViewById(R.id.ratingbar_readonly);
        mEditTextDestination = findViewById(R.id.edittext_destination_readonly);
        mEditTextTripName = findViewById(R.id.edittext_tripname_readonly);

        mSeekBarPrice.setEnabled(false);
        mEditTextTripName.setEnabled(false);
        mEditTextDestination.setEnabled(false);
        mButtonStartDate.setEnabled(false);
        mButtonEndDate.setEnabled(false);

    }
}
